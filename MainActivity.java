
Tambahkan di MainActivity.Java
//Urang nulisna di daerah sini 
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//To do weh
 String baseUrl = "https://raw.githubusercontent.com/Sahpakbar112/JSONADS/main/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<AdsResponse> call = apiService.getAds();
        call.enqueue(new Callback<AdsResponse>() {
            @Override
            public void onResponse(Call<AdsResponse> call, Response<AdsResponse> response) {
                if (response.isSuccessful()) {
                    AdsResponse adsResponse = response.body();
                    if (adsResponse != null) {
                        List<Ad> ads = adsResponse.getAds();
                        for (Ad ad : ads) {
                            Log.d("MainActivity", "Banner ID: " + ad.getBanner_id());
                            Log.d("MainActivity", "Interstitial ID: " + ad.getInterstitial_id());
                            Log.d("MainActivity", "Reward ID: " + ad.getReward_id());
                            Log.d("MainActivity", "Natives ID: " + ad.getNatives_id());
                            Log.d("MainActivity", "Open App ID: " + ad.getOpen_app_id());
                            Log.d("MainActivity", "Interval Interstitial: " + ad.getInterval_interstitial());
                            Log.d("MainActivity", "Channel ID: " + ad.getChannel_id());
                            Log.d("MainActivity", "Status App: " + ad.getStatus_app());
                            Log.d("MainActivity", "Link Redirect: " + ad.getLink_redirect());
                        }
                    }
                } else {
                    Log.e("MainActivity", "Response not successful: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<AdsResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
