package tw.edu.testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Boundary Value Testing（邊界值測試，UT12 p28）：測試 {@link CourseRegistrationPolicy#calculateFee(int, boolean)}。
 * <p><b>明確定義有效範圍：</b>
 * <ul>
 *   <li>報名人數 (studentCount)：有效範圍為 [1, N] 人（請依 spec.md 填入實際上限 N，以下假設 N = 10）。</li>
 *   <li>無效範圍：studentCount &lt; 1（如 0、負數）或 studentCount &gt; N（超過人數上限）。</li>
 *   <li>課程類型 (advancedCourse)：false（標準課程每人 100，第 2 人起折 20）、true（進階課程每人 200，第 2 人起折 20）。</li>
 * </ul>
 * <p>測試涵蓋下界附近 (min-1, min, min+1) 與上界附近 (max-1, max, max+1)。
 */
class CourseRegistrationBoundaryTest {

    private final CourseRegistrationPolicy policy = new CourseRegistrationPolicy();

    // --- 下界附近測試 (min - 1, min, min + 1) ---

    /**
     * BV1：下界減 1 (min - 1 = 0 人，無效邊界附近值)。
     * <ol>
     *   <li>Input values: studentCount=0, advancedCourse=false.</li>
     *   <li>Expected result: 0.</li>
     *   <li>Test program's result: printed below.</li>
     *   <li>Criteria analysis: Lower boundary - 1 (0 筆資料).</li>
     * </ol>
     */
    @Test
    void givenZeroStudents_whenCalculateFee_then0() {
        long actual = policy.calculateFee(0, false);
        System.out.println("BV1 (min-1=0): actual=" + actual);
        assertEquals(0L, actual);
    }

    /**
     * BV2：下界值 (min = 1 人，有效邊界值)。
     */
    @Test
    void givenOneStudent_whenCalculateFee_then100() {
        long actual = policy.calculateFee(1, false);
        System.out.println("BV2 (min=1): actual=" + actual);
        assertEquals(100L, actual);
    }

    /**
     * BV3：下界加 1 (min + 1 = 2 人，有效邊界附近值)。
     */
    @Test
    void givenTwoStudents_whenCalculateFee_then180() {
        long actual = policy.calculateFee(2, false);
        System.out.println("BV3 (min+1=2): actual=" + actual);
        assertEquals(180L, actual);
    }

    // --- 上界附近測試 (max - 1, max, max + 1，假設上限 N = 10，請依 spec.md 調整) ---

    /**
     * BV4：上界減 1 (max - 1 = N - 1 人，有效邊界附近值)。
     */
    @Test
    void givenMaxMinusOneStudents_whenCalculateFee_thenValid() {
        int n = 10; // 請依 spec.md 的實際上限修改
        long expected = 100L + (n - 2) * 80L;
        long actual = policy.calculateFee(n - 1, false);
        System.out.println("BV4 (max-1=" + (n - 1) + "): actual=" + actual);
        assertEquals(expected, actual);
    }

    /**
     * BV5：上界值 (max = N 人，有效邊界值)。
     */
    @Test
    void givenMaxStudents_whenCalculateFee_thenValid() {
        int n = 10; // 請依 spec.md 的實際上限修改
        long expected = 100L + (n - 2) * 80L;
        long actual = policy.calculateFee(n, false);
        System.out.println("BV5 (max=" + n + "): actual=" + actual);
        assertEquals(expected, actual);
    }

    /**
     * BV6：上界加 1 (max + 1 = N + 1 人，無效邊界附近值)。
     */
    @Test
    void givenMaxPlusOneStudents_whenCalculateFee_thenInvalid() {
        int n = 10; // 請依 spec.md 的實際上限修改
        // 請依 spec.md 確認超過上限 N 時是回傳 0、上限費用或拋出 IllegalArgumentException
        long actual = policy.calculateFee(n + 1, false);
        System.out.println("BV6 (max+1=" + (n + 1) + "): actual=" + actual);
    }
}
