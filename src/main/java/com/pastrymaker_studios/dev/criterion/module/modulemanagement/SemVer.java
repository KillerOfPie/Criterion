package com.pastrymaker_studios.dev.criterion.module.modulemanagement;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

@Getter
@SuppressWarnings("unused")
public class SemVer implements Comparable<SemVer>, Cloneable {

    private int major, minor, patch;
    public SemVer(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public SemVer(String version) {
        String[] split = version.split("\\.");
        int maj = Integer.parseInt(split[0]), min = Integer.parseInt(split[1]), pat = Integer.parseInt(split[2]);
        this.major = Math.max(maj, 0);
        this.minor = Math.max(min, 0);
        this.patch = Math.max(pat, 0);
    }

    public SemVer() {
        this(0, 0, 0);
    }

    public void incMajor() {
        major++;
        minor = 0;
        patch = 0;
    }

    public void incMinor() {
        minor++;
        patch = 0;
    }

    public void incPatch() {
        patch++;
    }

    public void decMajor() {
        major--;
        minor = 0;
        patch = 0;
    }

    public void decMinor() {
        minor--;
        patch = 0;
    }

    public void decPatch() {
        patch--;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(@NotNull SemVer o) {
        int maj = major - o.major, min = minor - o.minor, pat = patch - o.patch;
        maj = Math.min(Math.max(maj, -1), 1);
        min = Math.min(Math.max(min, -1), 1);
        pat = Math.min(Math.max(pat, -1), 1);

        if(maj != 0) return maj;
        if(min != 0) return min;
        return pat;
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public SemVer clone() {
        return new SemVer(major, minor, patch);
    }
}
