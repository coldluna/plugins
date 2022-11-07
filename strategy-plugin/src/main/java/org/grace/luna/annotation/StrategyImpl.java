package org.grace.luna.annotation;

import java.lang.annotation.Annotation;

/**
 * @author Alex Wang
 * Created on 2022/11/1 10:33:10
 */
public class StrategyImpl implements Strategy {

    private final String group;

    private final String trait;

    public StrategyImpl(String group, String trait) {
        this.group = group;
        this.trait = trait;
    }

    @Override
    public String group() {
        return group;
    }

    @Override
    public String trait() {
        return trait;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Strategy.class;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Strategy)) {
            return false;
        }

        Strategy another = (Strategy) obj;
        return group.equals(another.group()) && trait.equals(another.trait());
    }

    /**
     * WARNING:This method must be consistent with {@link Annotation#hashCode()}
     * The hash code of an annotation member is (127 times the hash code
     * of the member-name as computed by {@link String#hashCode()}) XOR
     * the hash code of the member-value.
     * See the detail of hashCode method via {@link Annotation#hashCode()}
     * @return
     */
    @Override
    public int hashCode() {
        int result = 0;
        result += (Byte.MAX_VALUE * "group".hashCode()) ^ this.group.hashCode();
        result += (Byte.MAX_VALUE * "trait".hashCode()) ^ this.trait.hashCode();
        return result;
    }

}
