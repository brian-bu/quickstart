package net.brian.coding.designpatterns.builder;

public class NutritionFactsConcreteBuilder2 {
	@SuppressWarnings("unused")
	private final int servingSize;
	@SuppressWarnings("unused")
	private final int servings;
	@SuppressWarnings("unused")
	private final int calories;
	@SuppressWarnings("unused")
	private final int fat;
	@SuppressWarnings("unused")
	private final int sodium;
	@SuppressWarnings("unused")
	private final int carbohydrate;

	public static class Builder2 {
		// Required parameters.
		private final int servingSize;
		private final int servings;
		// Optional parameters.
		private int calories = 0;
		private int fat = 0;
		private int sodium = 0;
		private int carbohydrate = 0;

		public Builder2(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}

		public Builder2 calories(int val) {
			calories = val;
			return this;
		}

		public Builder2 fat(int val) {
			fat = val;
			return this;
		}

		public Builder2 sodium(int val) {
			sodium = val;
			return this;
		}

		public Builder2 carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}

		public NutritionFactsConcreteBuilder2 build() {
			return new NutritionFactsConcreteBuilder2(this);
		}

	}

	private NutritionFactsConcreteBuilder2(Builder2 builder) {
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
		servingSize = builder.servingSize;
	}
}
