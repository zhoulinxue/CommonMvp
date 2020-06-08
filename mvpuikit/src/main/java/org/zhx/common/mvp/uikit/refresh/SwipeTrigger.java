package org.zhx.common.mvp.uikit.refresh;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.uikit.refresh
 * @ClassName: SwipeTrigger
 * @Description:java类作用描述
 * @Author: zhouxue
 * @CreateDate: 2020/6/8 17:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/8 17:39
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public interface SwipeTrigger {
    /**
     * @param
     * @return
     * @method
     * @description 描述一下方法的作用
     * @date: 2020/6/8 17:40
     * @author: zhouxue
     */
    void onPrepare();

    /**
     * @param
     * @return
     * @method
     * @description 描述一下方法的作用
     * @date: 2020/6/8 17:40
     * @author: zhouxue
     */
    void onMove(int y, boolean isComplete, boolean automatic);

    /**
     * @param
     * @return
     * @method
     * @description 描述一下方法的作用
     * @date: 2020/6/8 17:40
     * @author: zhouxue
     */
    void onRelease();

    /**
     * @param
     * @return
     * @method
     * @description 描述一下方法的作用
     * @date: 2020/6/8 17:40
     * @author: zhouxue
     */
    void onComplete();

    /**
     * @param
     * @return
     * @method
     * @description 描述一下方法的作用
     * @date: 2020/6/8 17:40
     * @author: zhouxue
     */
    void onReset();
}
