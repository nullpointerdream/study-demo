package com.mycrawler.mycrawler.design.proxy;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class MyProxy {

    public static Object newProxyInstance() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        TypeSpec.Builder timeProxy = TypeSpec.classBuilder("TimeProxy").
                addSuperinterface(Flyable.class);
        FieldSpec flyable = FieldSpec.builder(Flyable.class, "flyable", Modifier.PRIVATE).build();
        timeProxy.addField(flyable);
        MethodSpec constructorMethodSpec = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC)
                .addParameter(Flyable.class, "flyable")
                .addStatement("this.flyable = flyable")
                .build();
        timeProxy.addMethod(constructorMethodSpec);
        for (Method declaredMethod : Flyable.class.getDeclaredMethods()) {
            MethodSpec build = MethodSpec.methodBuilder(declaredMethod.getName())
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .returns(declaredMethod.getReturnType())
                    .addStatement("long start = $T.currentTimeMillis()", System.class)
                    .addCode("\n")
                    .addStatement("this.flyable." + declaredMethod.getName() + "()")
                    .addCode("\n")
                    .addStatement("long end = $T.currentTimeMillis()", System.class)
                    .addStatement("$T.out.println(\"Fly Time =\" + (end - start))", System.class)
                    .build();
            timeProxy.addMethod(build);
        }
        JavaFile javaFile = JavaFile.builder("com.mycrawler.mycrawler.design.proxy", timeProxy.build()).build();
        // 为了看的更清楚，我将源码文件生成到桌面
        javaFile.writeTo(new File("/Users/chenjiale/Desktop/"));
        String sourcePath = "/Users/chenjiale/Desktop/";
// 编译
        JavaCompiler.compile(new File(sourcePath + "/com/mycrawler/mycrawler/design/proxy/TimeProxy.java"));
        URL[] urls = new URL[] {new URL("file:/" + sourcePath)};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass("com.mycrawler.mycrawler.design.proxy.TimeProxy");
        Constructor constructor = clazz.getConstructor(Flyable.class);
        return constructor.newInstance(new Bird());

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Flyable o = (Flyable) MyProxy.newProxyInstance();
        o.fly();
    }
}