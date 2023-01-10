package org.example;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.cha.ClassHierarchy;
import com.ibm.wala.ipa.cha.ClassHierarchyException;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.config.AnalysisScopeReader;
import com.ibm.wala.util.io.FileProvider;

import java.io.File;
import java.io.IOException;

public class Analyse {

    public static void main(String args[]) throws IOException, ClassHierarchyException, IllegalArgumentException, InvalidClassFileException, CancelException {
        String hbaseClient = "E:\\downloads\\SC_HW\\hw6\\static-analysis\\data\\hbase-client-3.0.0-alpha-4-SNAPSHOT.jar";
        String hbaseServer = "E:\\downloads\\SC_HW\\hw6\\static-analysis\\data\\hbase-server-3.0.0-alpha-4-SNAPSHOT.jar";
        String jarPath = "E:\\downloads\\SC_HW\\hw6\\static-analysis\\data\\apache-cassandra-4.2-SNAPSHOT.jar";

        System.out.print(jarPath);
        System.out.println();

        File exFile = new FileProvider().getFile("Java60RegressionExclusions.txt");
        AnalysisScope scope = AnalysisScopeReader.makeJavaBinaryAnalysisScope(hbaseClient, exFile);

        ClassHierarchy cha = ClassHierarchyFactory.make(scope);

        int num = 0;
        for(IClass klass : cha) {

            if (klass.getName().toString().contains("Replic")) {
                System.out.println(klass.getName().toString());
                num += 1;
            }
        }

        System.out.println("Class Name Contain Replica/Replication num: " + num);
    }
}
