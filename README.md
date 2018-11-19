# RxRetrofit
RxRetrofit

# 初始化：
    RetrofitFactory.init(BaseApi.baseUrl);
    RetrofitFactory.init(BaseApi.baseUrl, BaseApi.getHeaderData());

  ## BaseApi
    public class BaseApi {

      public static String baseUrl = "http://192.168.2.135:8888/";

      public static HashMap<String, String> getHeaderData() {
          HashMap<String, String> headerDataMap = new HashMap<>();
          headerDataMap.put("token", "112233445566778899");
          headerDataMap.put("userCode", "381518188");
          headerDataMap.put("userName", "LiangYT");
          return headerDataMap;
        }
    }

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
 
# 上传：
   ## 单文件
    @Multipart
    @POST("slogan/user/updateUserAvatar")
    Observable<ResultData<UserEntity>> uploadFile(@Query("userCode") String userCode, @Part MultipartBody.Part file);
    
    // 传单文件文件和键值对
    File file = new File("/storage/emulated/0/Download/wKgANVvEPSeASGEFAAQ7wQP8jK4342.png");
    RetrofitFactory.init(BaseApi.baseUrl).create(AllApi.class)
            .uploadFile("381518188", RetrofitParam.createFileParam("file", file))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<ResultData<UserEntity>>() {
            });
     后台：       
    @PostMapping("/updateUserAvatar")
    public ResultData updateUserAvatar(@RequestParam("userCode") String userCode, @RequestParam("file") MultipartFile avatarFile){}
    
    
            
