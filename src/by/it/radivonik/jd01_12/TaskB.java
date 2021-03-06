package by.it.radivonik.jd01_12;

import java.util.*;
import java.util.regex.*;

/**
 * Created by Radivonik on 20.03.2017.
 * Класс, реализующий задания варианта B
 */
public class TaskB {
    /**
     * Метод, релизующий задание B1
     */
    public void runB1() {
        Map<String,Integer> wordCount = new TreeMap<>();
        Pattern p = Pattern.compile("[a-zA-Z']+");
        Matcher m = p.matcher(Data.textB1);
        while (m.find()) {
            String str = m.group();
            if (wordCount.containsKey(str)) {
                int count = wordCount.get(str);
                wordCount.put(str,count+1);
            }
            else
                wordCount.put(str,1);
        }

        // System.out.println(wordCount);
        // Нестандартный вывод списка частоты встречаемости слов
        int i = 0;
        for (Map.Entry<String,Integer> item:  wordCount.entrySet()) {
            if (i == 0)
                System.out.print("{");
            System.out.printf("%s = %d",item.getKey(),item.getValue());
            i++;
            if (i < wordCount.size()) {
                System.out.print(", ");
                if (i%10 == 0)
                    System.out.print("\n");
            }
            else
                System.out.print("}\n");
        }
    }

    /**
     * Метод, релизующий задание B2
     */
    public void runB2() {
        for (int i = 0; i < 4; i++) {
            int n = 100;
            switch(i) {
                case 1:
                    n = 1000; break;
                case 2:
                    n = 10000; break;
                case 3:
                    n = 100000; break;
            }

            ArrayList<Integer> listArray = new ArrayList<Integer>(n);
            LinkedList<Integer> listLinked = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                listArray.add(j+1);
                listLinked.add(j+1);
            }

            processArray(listArray);
            processLinked(listLinked);
        }
    }

    /**
     * Метод, релизующий задание B2 для ArrayList
     * @param array список типа ArrayList
     */
    private void processArray(ArrayList array) {
        processB2(array);
    }

    /**
     * Метод, релизующий задание B2 для LinkedList
     * @param linked список типа LinkedList
     */
    private void processLinked(LinkedList linked) {
        processB2(linked);
    }

    /**
     * Метод, релизующий задание B2 для любых наследников List
     * @param list список, наследуемый от List
     */
    private void processB2(List list) {
        System.out.printf("Счет для %s из %d элементов... ",list.getClass().getName(),list.size());
        Timer t = new Timer();
        boolean lastRemove = true;
        while(list.size() > 1) {
            int c = lastRemove ? 1 : 0; // определяется начинать ли удаление с 1-го элемента списка (чтобы реализовать удаление "по кругу")
            for (Iterator it = list.iterator(); it.hasNext(); c++) {
                it.next();
                if (c%2 == 0) {
                    lastRemove = !it.hasNext();
                    it.remove();
                }
            }
        }
        System.out.println(t);
    }

    /**
     * Класс для определения интервала времени
     */
    private static class Timer {
        private long timeBegin;
        public Timer() {
            timeBegin = System.nanoTime();
        }
        public String toString() {
            double timeDelta = (double)(System.nanoTime() - timeBegin) / 1000;
            timeBegin = System.nanoTime();
            return "Затрачено " + timeDelta + " микросекунд";
        }
    }
}
