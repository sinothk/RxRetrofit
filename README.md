# RxRetrofit
RxRetrofit

# 提交表单：
  
   ## API:
        @POST("slogan/user/findUsersByKeyword")
        Observable<ResultData<PageData<List<UserEntity>>>> findUsersByKeyword(@Query("keyword") String keyword);
  
   ## JAVA:
          RetrofitFactory.init(BaseApi.baseUrl).create(NearbyApi.class)
          .findUsersByKeyword("keyword")
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Subscriber<ResultData<PageData<List<UserEntity>>>>() {
              @Override
              public void onCompleted() {
                  Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
              }

              @Override
              public void onError(Throwable e) {
                  Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
              }

              @Override
              public void onNext(ResultData<PageData<List<UserEntity>>> resultData) {
                  if (resultData != null) {
                  }
              }
          });
                
  ## 后台
    /**
     * 查询用户：根据userCode
     *
     * @param keyword keyword
     * @return 返回
     */
    @PostMapping("/findUsersByKeyword")
    public ResultData findUsersByKeyword(@RequestParam("keyword") String keyword) {//
        //http://127.0.0.1:8888/slogan/user/findUsersByKeyword
        return resultData;
    }      
                
# 提交实体：
  ## API:
    @POST("slogan/user/updateUser")
    Observable<ResultData<UserEntity>> updateUser(@Body UserEntity user);
    
  ## JAVA:
    UserEntity user = new UserEntity();
    user.setId(111);
    user.setUserName("LiangYT");

    RetrofitFactory.init(BaseApi.baseUrl).create(NearbyApi.class)
            .updateUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<ResultData<UserEntity>>() {
                @Override
                public void onCompleted() {
                    Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(ResultData<UserEntity> resultData) {
                    if (resultData != null) {
                    }
                }
            });
            
 ## 后台
    @PostMapping("/updateUser")
    public ResultData updateUser(@RequestBody UserVo user) {//
        //http://127.0.0.1:8888/slogan/user/updateUser
        return userService.updateUser(user);
    }
 
