# ArrowStepView-master
[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.html)

自定义View之箭头步骤指示器，用于流程操作
# 效果图
<img src="https://github.com/kellysong/ArrowStepView-master/blob/master/screenshot/Screenshot_2019-04-03-17-58-22-132_com.sjl.arrows.png" width="30%" alt="加载中..."/>
# 引用

##Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }

##Step 2. Add the dependency

	dependencies {
		        implementation 'com.github.kellysong:ArrowStepView-master:1.0.0'
		}

或者引用本地lib
	
	implementation project(':library')

# 使用
1. 在布局文件中添加ArrowStepView，可以设置自定义属性
	
	    <com.sjl.stepview.widget.ArrowStepView
			xmlns:app="http://schemas.android.com/apk/res-auto"
	        android:id="@+id/step_view"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"
	        android:background="@android:color/white"
	        app:asv_current_select_step="1" />


	## Attributes属性（布局文件中调用）
	
	|Attributes|forma|describe
	|---|---|---|
	|asv_circle_radius| dimension|步骤指示器圆圈半径，默认6dp
	|asv_circle_text_size| dimension|步骤指示器圆圈文字大小，默认10sp
	|asv_title_size| dimension|步骤指示器标题文字大小，默认12sp
	|asv_text_color| color|步骤指示器题颜色
	|asv_height| dimension|步骤指示器高度，默认38dp
	|asv_triangle_width| dimension|步骤指示器箭头宽度，默认15dp
	|asv_text_margin_left| dimension|步骤指示器圆圈和标题左边距，默认8dp
	|asv_current_select_color| color|步骤指示器当前选中背景色（终止背景色）
	|asv_default_select_color| color|步骤指示器默认背景色（起始背景色）
	|asv_current_select_step| integer|步骤指示器当前选择步骤，默认1


2. 设置数据
	
    	  List<String> steps = Arrays.asList(new String[]{"选择金额", "支付", "贴卡充值"});
     	  arrowStepView.setSteps(steps);
	  
3. 设置当前选择步骤
	
	      arrowStepView.selectedCurrentStep(nextStep);
	  