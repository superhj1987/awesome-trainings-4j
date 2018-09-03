package me.rowkey.trainings.effective_java;

import java.util.WeakHashMap;

//��ʵ��ö������
enum Elvis {
    INSTANCE;

    public int b = 200;
    private int a = 100;

    public void leaveTheBuilding() {
        System.out.println(a);
    }
}

//�ӿ�
interface IBuilder<T> {
    public T build();
}

//Builderģʽ
@SuppressWarnings("unused")
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
    }

    public static void main(String[] args) {
        NutritionFacts.Builder builder = new NutritionFacts.Builder(240, 0);
        NutritionFacts n = builder.calories(100).fat(100).build();
        System.out.println(n.calories);

        String a = "hj";
        String b = "hj";

        Elvis e = Elvis.INSTANCE;
        Elvis f = Elvis.INSTANCE;
        if (a == b) {
            System.out.println(e.b);
        }
        WeakHashMap<String, String> whmTemp = new WeakHashMap<String, String>();
    }

    public static class Builder implements IBuilder<NutritionFacts> {
        private final int servingSize;
        private final int servings;

        private int calories = 0;
        private int fat = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }

    }
}
