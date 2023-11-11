package com.dnc.server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    private static List<Integer> B = new ArrayList<>(); // 전역 변수
    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>();
        A.add(1);
        A.add(2);
        A.add(3);

        // A 리스트의 요소를 B 리스트로 복사
        B = A.stream().collect(Collectors.toList());

        // A 리스트의 필드를 변경
        A.set(0, 99);

        System.out.println("A: " + A); // 출력: A: [99, 2, 3]
        System.out.println("B: " + B); // 출력: B: [1, 2, 3] (B 리스트는 영향을 받지 않음)
    }
}
