# RxRetrofit
# 依赖
    maven { url 'https://jitpack.io' }

    implementation 'com.github.sinothk:RxRetrofit:4.1.0308'

# 初始化：
    RetrofitFactory.init(BaseApi.baseUrl).***
    RetrofitFactory.init(BaseApi.baseUrl, BaseApi.getHeaderData()).***
    // 下载使用
    RetrofitFactory.init(BaseApi.baseUrl, Executors.newSingleThreadExecutor()).***
    
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
          RxRetrofit.init(BaseApi.baseUrl).create(NearbyApi.class)
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

    RxRetrofit.init(BaseApi.baseUrl).create(NearbyApi.class)
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
    API：
    @Multipart
    @POST("slogan/user/updateUserAvatar")
    Observable<ResultData<UserEntity>> uploadFile(@Query("userCode") String userCode, @Part MultipartBody.Part file);
    
    java:
    // 传单文件文件和键值对
    File file = new File("/storage/emulated/0/Download/wKgANVvEPSeASGEFAAQ7wQP8jK4342.png");
    RxRetrofit.init(BaseApi.baseUrl).create(AllApi.class)
            .uploadFile("381518188", RetrofitParam.createFileParam("file", file))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<ResultData<UserEntity>>() {
            });
     后台：       
    @PostMapping("/updateUserAvatar")
    public ResultData updateUserAvatar(@RequestParam("userCode") String userCode, @RequestParam("file") MultipartFile avatarFile){}
    
  ## 多文件
    API:
    @Multipart
    @POST("slogan/user/sendDaily")
    Observable<ResultData<UserEntity>> sendDaily(@Query("userCode") String userCode,  @PartMap() Map<String, RequestBody> maps);
    
    java:
    // 传单文件文件和实体
    File file = new File("/storage/emulated/0/Download/wKgANVvEPSeASGEFAAQ7wQP8jK4342.png");
        ArrayList<File> files = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            files.add(file);
        }

    RxRetrofit.init(BaseApi.baseUrl).create(AllApi.class)
    .sendDaily("381518188", RetrofitParam.createFileListParam("file", files))
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(new Subscriber<ResultData<UserEntity>>() {
    });
    
    后台：
    @PostMapping("/sendDaily")
    public ResultData sendDaily(@RequestParam("userCode") String userCode, @RequestParam("file") MultipartFile[] files) {
        if (files != null) {
            try {
                for (int i = 0; i < files.length; i++) {
                    File localFile = new File("D:/" + files[i].getOriginalFilename());
                    files[i].transferTo(localFile);
                }
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        } else {
            return ResultData.getError(ErrorCode.OPERATE_ERROR);
        }
    }
    
  ## 下载文件
    API：
        @Streaming
        @GET
        Call<ResponseBody> download(@Url String url);
    
    Java:
        // 文件下载
        String url = "c72c378e0a5d827ebd94d2c880da01ec.apk?attname=mgdj-release_2.6.3_19_1112.apk&sign=c64d18d5db3ee659c5962e9e3a52c643&t=5bf2783d";
        String path = "/storage/emulated/0/Download/21212.apk";

        RxRetrofit
        .init("http://app-global.pgyer.com/", Executors.newSingleThreadExecutor())
        .create(AllApi.class)
        .download(url)
        .enqueue(new DownloadCallback(MainActivity.this, path) {
            @Override
            public void onStart() { 
            }
            @Override
            public void onProgress(long currSize, long totalSize, int progress) {
            }
            @Override
            public void onFinish(String path) {
            }
            @Override
            public void onFail(String errorInfo) {
            }
        });
  
  
