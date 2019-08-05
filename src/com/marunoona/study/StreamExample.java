package com.marunoona.study;

import java.util.*;
import java.util.stream.*;

/**
 * Stream 핵심 메소드 활용 예제
 * <p>
 * Stream 특징
 * 1. 스트림은 데이터소스를 변경하지 않는다.
 * - 스트림은 데이터 소스로부터 데이터를 읽기만할 뿐, 데이터 소스를 변경하지 않는다.
 * 필요하다면, 정렬된 결과를 컬렉션이나 배열에 담아서 반환할 수 있다.
 * 2. 스트림은 일회용이다.
 * - 스트림은 Iterator처럼 일회용이다. iterator로 컬렌셕의 요소를 모두 읽고나면
 * 다시 사용할 수 없는 것처럼, 스트림도 한번 사용하면 닫혀서 다시 사용할 수 없다.
 * 필요하다면, 스트림들을 다시 생성해야한다.
 * 3. 스트림은 작업을 내부 반족으로 처리한다.
 * - 스트림을 이용한 작업이 간결할 수 있는 비결중의 하나가 바로 '내부' 반복이다.
 * 내부 반복이라는 것은 반복문을 메소드의 내부에 숨길 수 있다는것을 의미한다.
 */
public class StreamExample {

    /**
     * map() 활용 예제
     * map(): 입력 컬렉션을 출력 컬렉션으로 매핑하거나 변경할 때 유용함
     * : 스트림의 요소에 저장된 값 중에 원하는 필드로만 뽑아내거나 특정 형대로 변환할 때
     *
     * @param arr
     */
    public static void runMapExample(String[] arr) {
        // java7
        System.out.println("/// Java7");
        List<String> wordList = Arrays.asList(arr);
        for (String string : wordList) {
            System.out.println(string.toUpperCase());
        }

        // java8
        System.out.println("/// Java8");
        // 배열 스트림 생성
        //Stream<String> stream = Arrays.stream(arr);
        // 리스트 스트리 생성
        Stream<String> stream2 = wordList.stream();
        // Stream with Lambda
        //stream.map(String::toUpperCase).forEach(System.out::println);
        stream2.map(String::toUpperCase).forEach(System.out::println);
    }

    /**
     * flatMap() 활용 예제
     * 중첩된 리스트의 중첩 구조를 제거하여 원하는 리스트 형식을 만들 수 있음
     */
    public static void runFlatMapExample() {
        // flatMap 활용예제
        String[] arr1 = {"a", "b", "c"};
        String[] arr2 = {"d", "e", "f"};
        List<List<String>> overlapList = Arrays.asList(
                Arrays.asList(arr1), Arrays.asList(arr2)
        ); // [[a,b,c],[d,e,f]]
        List<String> flatList = overlapList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(flatList); // [a,b,c,d,e,f]
    }

    /**
     * filter() 활용 예제
     * filter() : 컬렉션을 조건에 의한 선택을 할 때 유용
     * filter 메서드는 boolean 결과를 리턴하는 람다표현식이 필요함
     *
     * @param arr
     */
    public static void runFilterExample(String[] arr) {
        List<String> list = Arrays.asList(arr);
        // java7
        System.out.println("/// Java7");
        List<String> startWithList7 = new ArrayList<>();
        List<String> containList7 = new ArrayList<>();
        for (String string : list) {
            if (string.startsWith("m"))
                startWithList7.add(string);
        }
        System.out.println(startWithList7);
        for (String string : list) {
            if (string.contains("b"))
                containList7.add(string);
        }
        System.out.println(containList7);

        // java8
        System.out.println("/// Java8");
        List<String> startWithList8 =
                list.stream().filter(str -> str.startsWith("m")).collect(Collectors.toList());
        System.out.println(startWithList8);
        List<String> containList8 =
                list.stream().filter(str -> str.contains("b")).collect(Collectors.toList());
        System.out.println(containList8);
    }

    /**
     * reduce() 활용 예제
     * reduce(): 앨리먼트를 비교하고 컬렉션에서 하나의 값으로 연선한다.
     */
    public static void runReduceExample() {
        // 인자를 하나만 받는 경우
        OptionalInt reduced = IntStream.range(1, 4)
                .reduce(Integer::sum);
        if (reduced.isPresent())
            System.out.println(reduced.getAsInt());

        // Q. 리스트 값 중 최대값 찾기
        Integer[] integerArray = {1, 2, 5, 8, 19, 3, 11};
        List<Integer> integerList = Arrays.asList(integerArray);
        Optional<Integer> stream = integerList.stream().reduce(Integer::max);
        stream.ifPresent(System.out::println);

        // 인자를 두개 받는 경우
        int reducedTwoParams = IntStream.range(1, 4)
                .reduce(10, Integer::sum);
        System.out.println(reducedTwoParams);

        // 인자를 세개 받는 경우
        // Combiner 는 병렬 처리 시 각자 다른 쓰레드에서 실행한 결과를 마지막에 합치는 단계입니다.
        // 따라서 병렬 스트림에서만 동작
        Integer reduceParallel = Arrays.asList(1, 2, 3)
                .parallelStream()
                .reduce(10,     //identity
                        Integer::sum,   // accumulator
                        (a, b) -> {
                            System.out.println("combiner was called");
                            return a + b;
                        });
        System.out.println(reduceParallel);

        // Q. 리스트 중 특정 문자열보다 길이가 길고 리스트 중 가장 긴 문자열을 출력
        List<String> startbucks = Arrays.asList("coffee", "espresso",
                "teanava", "frappuccino", "blended", "chocolate");
        // java7
        System.out.println("/// Java7");
        String longElement7 = "";
        for (String string : startbucks) {
            if (("coffee".length() <= string.length())
                    && (longElement7.length() <= string.length())) {
                longElement7 = string;
            }
        }
        System.out.println(longElement7);

        // java8
        System.out.println("/// Java8");
        String longElement8 = startbucks.stream()
                .reduce("coffee", (name1, name2) ->
                        name1.length() >= name2.length() ? name1 : name2);
        System.out.println(longElement8);
    }

    /**
     * collect() 활용 예제
     * collect(): 스트림의 요소를 수집하는 최종 연산으로  reduce() 와 유사하다.
     * collect는 여러 convenience method를 제공한다.
     * collect()가 스트림의 요소를 수집하려면, 어떻게 수집할 것인가에 대한 방법이 정의되어 있어야 하는데,
     * 이방법을 정의한 것이 바로 컬렉터(collector)이다.
     * 컬렉터는 Collector인터페이스를 구현한 것으로, 직접 구현할 수도 있고 미리 작성된 것을 사용할 수도 있다.
     */
    public static void runCollectExample() {
        List<Product> productList = Arrays.asList(
                new Product("orange", 14),
                new Product("bread", 23),
                new Product("sugar", 13),
                new Product("lemon", 14),
                new Product("potato", 9),
                new Product("salt", 13));

        List<String> collectorCollection = productList.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        System.out.println(collectorCollection);

        String listToString = productList.stream()
                .map(Product::getName)
                .collect(Collectors.joining());
        System.out.println(listToString);
        listToString = productList.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", ", "<", ">"));
        System.out.println(listToString);

        Integer sumPrice = productList.stream()
                .collect(Collectors.summingInt(Product::getPrice));
        System.out.println("가격 합계:" + sumPrice);
        Integer sumPriceUseMap = productList.stream().mapToInt(Product::getPrice).sum();
        System.out.println("가격 합계:" + sumPriceUseMap);

        Double averagePrice = productList.stream()
                .collect(Collectors.averagingInt(Product::getPrice));
        System.out.println("가격 평균:" + averagePrice);
        IntSummaryStatistics statistics = productList.stream()
                .collect(Collectors.summarizingInt(Product::getPrice));

        System.out.println("가격 통계 정보:" + statistics);
        Map<Integer, List<Product>> collectorMapOfLists =
                productList.stream().collect(Collectors.groupingBy(Product::getPrice));

        Collector<Product, ?, LinkedList<Product>> toLinkedList =
                Collector.of(LinkedList::new,
                        LinkedList::add,
                        (first, second) -> {
                            first.addAll(second);
                            return first;
                        });
        LinkedList<Product> linkedListOfPersons = productList.stream()
                .collect(toLinkedList);
        for (Product product : linkedListOfPersons) {
            System.out.println(product.getName()+","+product.getPrice());
        }

    }

    public static void main(String[] args) {
        String[] arr = {"apple", "banana", "grape", "strawberry", "melon", "mango"};
        System.out.println("#1. map() 활용");
        runMapExample(arr);
        System.out.println("--------------------");
        System.out.println("#2. flatMap() 활용");
        runFlatMapExample();
        System.out.println("--------------------");
        System.out.println("#3. filter() 활용");
        runFilterExample(arr);
        System.out.println("--------------------");
        System.out.println("#4. reduce() 활용");
        runReduceExample();
        System.out.println("--------------------");
        System.out.println("#5. collect() 활용");
        runCollectExample();
    }
}
