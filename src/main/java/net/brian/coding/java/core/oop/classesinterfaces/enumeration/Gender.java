package net.brian.coding.java.core.oop.classesinterfaces.enumeration;

import net.brian.coding.java.core.jdk.exception.ParameterTypeMismatchException;
/**
 * 
 * 对Thinking in Java和枚举enum相关的知识点的汇总，形成示例代码
 *
 */
public enum Gender {
	// Won't compile cause we don't have the suitable constructor.
	// O,
	M("M"), F("F");
	private String g;

	private Gender(String g) {
		this.g = g;
	}

	// Have to be static.
	/**
	 * For enumeration conversion and valid String check.
	 * 
	 * @return Gender
	 * @param String
	 * @exception ParameterTypeMismatchException
	 **/
	public static Gender homemadeValueOf(String value) throws ParameterTypeMismatchException {
		if (null != value) {
			switch (value.toUpperCase()) {
			case "M":
				return M;
			case "F":
				return F;
			default:
				throw new ParameterTypeMismatchException("The value of gender can only be 'M', 'F', 'm' or 'f'");
			}
		} else {
			throw new NullPointerException("The value of gender is mandatory!");
		}
	}

	// Have to be non-static.
	public String homemadeValue() {
		return this.g;
	}
}

class EnumGenderDemo {
	@SuppressWarnings("unused")
	public static void testEnumValueOf() throws ParameterTypeMismatchException {
		Gender f1 = Enum.valueOf(Gender.class, "F");
		Gender m1 = Enum.valueOf(Gender.class, "M");
		System.out.println("If (f1 == Gender.F):: " + (f1 == Gender.F));
		System.out.println("If (m1 == Gender.M):: " + (m1 == Gender.M));
		// To convert String to enum.
		Gender f2 = Gender.homemadeValueOf("F");
		Gender m2 = Gender.homemadeValueOf("M");
		// To convert enum to String.
		String male = Gender.M.homemadeValue();
		String female = Gender.F.homemadeValue();
	}
}