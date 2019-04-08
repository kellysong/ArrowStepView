# ArrowStepView-master
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
# 使用
1. 设置数据
	
    	  List<String> steps = Arrays.asList(new String[]{"选择金额", "支付", "贴卡充值"});
     	  arrowStepView.setSteps(steps);
	  
2. 下一步
	
	      arrowStepView.selectedCurrentStep(nextStep);
	  