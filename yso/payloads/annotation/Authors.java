package yso.payloads.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authors {
    String FROHOFF = "frohoff";
    String PWNTESTER = "pwntester";
    String CSCHNEIDER4711 = "cschneider4711";
    String MBECHLER = "mbechler";
    String JACKOFMOSTTRADES = "JackOfMostTrades";
    String MATTHIASKAISER = "matthias_kaiser";
    String GEBL = "gebl" ;
    String JACOBAINES = "jacob-baines";
    String JASINNER = "jasinner";
    String KULLRICH = "kai_ullrich";
    String TINT0 = "_tint0";
    String SCRISTALLI = "scristalli";
    String HANYRAX = "hanyrax";
    String EDOARDOVIGNATI = "EdoardoVignati";
    String JANG = "Jang";
    String ARTSPLOIT = "artsploit";
    String WH1T3P1G = "wh1t3p1g";
    String NAVALORENZO = "navalorenzo";
    String BEIYING ="Beiying";
    String KORLR = "koalr";
    String PHITHON = "phith0n";
    String NOPOINT = "NoPoint";
    String C0NY1 = "c0ny1";
    String yulegeyu = "yulegeyu";
    String None = "None";
    String KEZIBEI = "kezibei";

    String[] value() default {};

    public static class Utils {
        public static String[] getAuthors(AnnotatedElement annotated) {
            Authors authors = annotated.getAnnotation(Authors.class);
            if (authors != null && authors.value() != null) {
                return authors.value();
            } else {
                return new String[0];
            }
        }
    }
}
