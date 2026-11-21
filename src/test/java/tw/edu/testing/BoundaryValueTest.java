package tw.edu.testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Boundary Value 邊界值測試 (Software Testing UT12 p28)
 *
 * 【明確定義程式所用資料的有效範圍】
 * 1. 報名人數 studentCount (整數): 有效範圍 [1, 30]
 *    - 測試值: 0, 1, 2 (下界附近) 與 29, 30, 31 (上界附近)
 * 2. 已修課程陣列 completedCourses 筆數: 有效筆數 [0, N] 筆 (定義 N = 5)
 *    - 測試筆數: 0, 1 筆 與 4, 5, 6 筆 (N-1, N, N+1)
 */
public class CourseRegistrationBoundaryTest {

    private final CourseRegistrationPolicy policy = new CourseRegistrationPolicy();

    // 1. 整數有效範圍 [1, 30] 的邊界值與附近值 (0, 1, 2 與 29, 30, 31)
    @Test
    void testStudentCountBoundaryValues() {
        // 下界附近: 0, 1, 2
        assertEquals(0L, policy.calculateFee(0, false));     // min - 1 (無效)
        assertEquals(100L, policy.calculateFee(1, false));   // min     (有效下界)
        assertEquals(180L, policy.calculateFee(2, false));   // min + 1 (有效下界附近，觸發折扣)

        // 上界附近: 29, 30, 31
        assertEquals(2340L, policy.calculateFee(29, false)); // max - 1 (有效上界附近)
        assertEquals(2420L, policy.calculateFee(30, false)); // max     (有效上界)
        assertEquals(2500L, policy.calculateFee(31, false)); // max + 1 (超出定義有效上限)
    }

    // 2. 資料有效筆數 [0, 5] 筆的邊界值與附近值 (0, 1 筆 與 4, 5, 6 筆)
    @Test
    void testCompletedCoursesCountBoundaryValues() {
        // 0 筆資料
        assertFalse(policy.canEnroll(true, false, new String[]{}));
        // 1 筆資料
        assertTrue(policy.canEnroll(true, false, new String[]{"Programming"}));

        // N - 1 = 4 筆資料
        assertTrue(policy.canEnroll(true, false, new String[]{"C1", "C2", "C3", "Programming"}));
        // N = 5 筆資料
        assertTrue(policy.canEnroll(true, false, new String[]{"C1", "C2", "C3", "C4", "Programming"}));
        // N + 1 = 6 筆資料
        assertTrue(policy.canEnroll(true, false, new String[]{"C1", "C2", "C3", "C4", "C5", "Programming"}));
    }
}
