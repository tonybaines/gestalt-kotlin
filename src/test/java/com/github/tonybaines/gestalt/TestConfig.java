package com.github.tonybaines.gestalt;


import javax.validation.constraints.*;
import java.util.List;

public interface TestConfig {
    @NotNull()
    Integer getIntValue();

    String getStringValue();

    double getDoubleValue();

    @NotNull
    long getLongValue();

    Boolean isBooleanValue();

    Handed getHandedness();

    @NotNull
    SubConfigLevel1 getSubConfig();

    List<String> getStrings();

    List<SomeThing> getAllTheThings();

    @Default.EmptyList
    List<String> getMissing();

    @Comment("Does not exist")
    Integer getNonExistent();

    Integer getDeclaredAsAnIntegerButIsAString();

    String getSomethingDefinedTwice();

    @Default.String("default-value")
    String getNonExistentStringWithDefault();

    @Default.Integer(42)
    Integer getNonExistentIntegerWithDefault();

    @Default.Boolean(false)
    Boolean getNonExistentBooleanWithDefault();

    @Default.Double(42.5)
    Double getNonExistentDoubleWithDefault();

    /**
     * Enums need to have defaults defined
     * as String constants such that they
     * evaluate successfully from
     * <pre>MyEnum.valueOf(string)</pre>
     */
    @Default.Enum("right")
    Handed getNonExistentEnumWithDefault();

    @Default.Enum("sideways")
    Handed getDefaultedValueWithBadDefinition();

    String getPropertyDefinedOnlyInGroovyConfig();

    String getPropertyDefinedAllConfigSources();

    @Size(min = 1, max = 2)
    @Default.String("foo")
    String getStringValueWhoseDefaultBreaksValidation();

    @Max(10)
    @Min(1)
    Integer getIntegerThatIsTooLarge();

    public static interface SubConfigLevel1 {
        Integer getIntValue();

        @AssertTrue
        @Default.Boolean(false)
        boolean getBooleanValueWhoseValueBreaksValidation();

        @Pattern(regexp = "f.*")
        @Default.String("fin")
        String getValueWhichIsDefinedToBreakValidationButHasADefault();

        SubConfigLevel2 getL2();
    }

    interface SubConfigLevel2 {
        @Default.String("foobar")
        String getLevel3Property();

        String getNonExistent();
    }

    public static interface SomeThing {
        String getId();

        String getStringValue();
    }
}
