package com.sinothk.rxRetrofitDemo.temp;

/**
 * <pre>
 *  创建:  LiangYT 2018/6/23/023 on 0:27
 *  项目: RxRetrofitLib
 *  描述:
 *  更新:
 * <pre>
 */
public class P {

//    RetrofitApiFactory.createApi(HomeApi.class)
//            .viewRecruit(userId, token, proId)
//                .compose(((RxAppCompatActivity) view).<BaseData<RecruitInfo>>bindUntilEvent(ActivityEvent.DESTROY))
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new ReSubscriber<BaseData<RecruitInfo>>() {
//
//        @Override
//        protected void onError(ApiException ex) {
//            if (view != null) {
//                view.hideProgress();
//                view.showError(ex.getMessage());
//            }
//        }
//
//        @Override
//        public void onCompleted() {
//            if (view != null)
//                view.hideProgress();
//        }
//
//        @Override
//        public void onNext(BaseData<RecruitInfo> data) {
//            if (data.getErrcode() == Constant.SUCCESS_CODE) {
//                if (view != null) {
//                    view.showRecruitInfo(data.getData());
//                }
//            } else if(data.getErrcode() == Constant.SUCCESS_CODE_SINGLE){
//                JumpUtil.overlay(MApplication.getInstance().appManager.currentActivity(), LoginActivity.class);
//
//                view.showError(data.getErrmsg());
//                LoginInfoCache.clear();
//                AppManager.getAppManager().finishAllActivity();
//
//            }else if (view != null) {
//                view.showError(data.getErrmsg());
//            }
//        }
//    });
}
