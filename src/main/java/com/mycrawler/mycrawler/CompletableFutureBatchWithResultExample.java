package com.mycrawler.mycrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureBatchWithResultExample {

    // 模拟接口A的批量调用
    private static CompletableFuture<List<String>> callApiABatch(List<Integer> ids) {
        return CompletableFuture.supplyAsync(() -> {
            // 实际调用接口A的批量逻辑
            return ids.stream().map(id -> "Result from API A for ID: " + id).collect(Collectors.toList());
        });
    }

    // 模拟接口B的批量调用
    private static CompletableFuture<List<String>> callApiBBatch(List<Integer> ids) {
        return CompletableFuture.supplyAsync(() -> {
            // 实际调用接口B的批量逻辑
            return ids.stream().map(id -> "Result from API B for ID: " + id).collect(Collectors.toList());
        });
    }

    public static void main(String[] args) {
        // 调用接口获取所有的ID列表
        List<Integer> allIds = getAllIds();

        // 切分ID集合为大小为20的子集
        List<List<Integer>> idChunks = chunkList(allIds, 20);

        // 使用CompletableFuture进行异步批量调用和合并结果
        List<CompletableFuture<List<String>>> combinedResults = idChunks.stream()
                .map(chunk -> {
                    CompletableFuture<List<String>> apiAResults = callApiABatch(chunk);
                    CompletableFuture<List<String>> apiBResults = callApiBBatch(chunk);

                    // 合并接口A和接口B的批量结果
                    CompletableFuture<List<String>> combinedResult = apiAResults.thenCombine(apiBResults, (resultA, resultB) -> {
                        List<String> combinedList = new ArrayList<>();
                        for (int i = 0; i < chunk.size(); i++) {
                            combinedList.add(resultA.get(i) + "\n" + resultB.get(i));
                        }
                        return combinedList;
                    });

                    return combinedResult;
                })
                .collect(Collectors.toList());

        // 等待所有异步操作完成
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                combinedResults.toArray(new CompletableFuture[0])
        );

        // 获取所有结果
        List<String> finalResults = allOf.thenApplyAsync(v ->
                        combinedResults.stream()
                                .map(CompletableFuture::join)
                                .flatMap(List::stream)
                                .collect(Collectors.toList()))
                .join();

        // 输出最终合并的结果
        finalResults.forEach(System.out::println);
    }

    // 模拟获取所有的ID列表的方法
    private static List<Integer> getAllIds() {
        List<Integer> allIds = new ArrayList<>();
        // 实际获取ID列表的逻辑
        for (int i = 1; i <= 100; i++) {
            allIds.add(i);
        }
        return allIds;
    }

    // 将列表切分为指定大小的子列表
    private static <T> List<List<T>> chunkList(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            int end = Math.min(i + chunkSize, list.size());
            chunks.add(list.subList(i, end));
        }
        return chunks;
    }
}

