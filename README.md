<h1> Herb Reference Guide  App</h1>
<p>This app has been developed within the scope of Mobile course belonging to Metropolia Unviersity Of Applied Sciences. </p>

<h2> Developers and Maintainers </h2>
1. Giao Ngo <br>
2. Tai Nguyen <br>
3. Shayne Kandagor <br>

<h2>Project description</h2>
<p>The app is an Android standalone herb reference book that provides plenty of information about nutrition value, health benefits, warnings and food  suggestion of different types of plant to users. Besides that, there is area for users to add notes, update and delete their notes for future reference.</p>
<p> To enrich user's  interaction and experience with the app, we added a custom-built camera with the help Android's Camera X Api, and created a specialised gallery for storing plant's images. The purpose of having camera and gallery is for those who would like to capture every images of plant they find interesting on the spot/at that moment and collect those images to the collection for keepsake. </p>

<h2> In-app technology</h2>
<p>Coding language: Java</p>
<p>Min sdk : 21, target sdk: 32</p>
<h2>User target</h2>
- The  app targets at Android users <br>
- For healthy eaters who are curious of the benefits of specific plant items <br>
- For adventurous travellers (mountain hikers, bikers, campers, forest explorers) to know  which plants are edible and which are poisonous  before touching/eating them  <br>
- For patients seeking for natural remedies and for medical students.

<h2>Document test case and Javadoc</h2>

1. [Document Test Case](https://docs.google.com/document/d/1dYyVx79O6v3mLDUfCLSHCA7NKpzyc5xV/edit?usp=sharing&ouid=114237886261926611764&rtpof=true&sd=true) <br>
2. [Javadoc](https://users.metropolia.fi/~phatn/HerbReferenceGuide/)
<h2>Study sources and code references</h2>
<h6>Layout and UI components</h6>
    - [RecyclerView & RecyclerView Adapter](https://www.youtube.com/watch?v=18VcnYN5_LM&ab_channel=Stevdza-San) <br>
    - [RecyclerView click listener (best performance)](https://www.youtube.com/watch?v=7GPUpvcU1FE&ab_channel=PracticalCoding) <br>
    - [Adding Card View with a textbox](https://developer.android.com/guide/topics/ui/layout/cardview)<br>
    - [Floating Action Button](https://developer.android.com/guide/topics/ui/floating-action-button) <br>
    - [Set menu on toolbar and meny item click listener](https://stackoverflow.com/questions/7479992/handling-a-menu-item-click-event-android) <br>
    - [App Launch Icon](https://stock.adobe.com/search/images?filters%5Bcontent_type%3Azip_vector%5D=1&filters%5Borientation%5D=vertical%2Csquare&filters%5Borientation_type%5D%5Bis_square%5D=true&filters%5Bcontent_type%3Aimage%5D=1&order=relevance&safe_search=1&limit=100&search_page=1&search_type=filter-select&acp=&aco=herbal+icon&k=herbal+icon&get_facets=1&asset_id=275377040) <br>
    - [Snackbar](https://material.io/components/snackbars/android#theming-snackbars) <br>

<h6>Fonts, Icon and Image sources used in app </h6>
    - Font: HeadlandOne-Regular by Google Fonts designed by Gary Lonergan. And Serif Monospace by Android. <br>
    - Icons : Unlimited usage of icons without author's permission and attribution. <a  href="https://publicdomainvectors.org/">PublicDomain
Vectors.com </a> <br>
    - Images: Unlimited  usage of images without author's permission and attribution. 1. <a  href="https://pixabay.com/">Pixabay</a> 2.<a href="https://unsplash.com/">Unsplash</a> 3.<a href="https://www.pexels.com/">Pexels</a> <br>

<h6>Backend</h6>
    - [Prepopulate Room Database](https://www.youtube.com/watch?v=pe28WeQ0VCc&ab_channel=Stevdza-San) <br>
    - [Launching an activity for result](https://developer.android.com/training/basics/intents/result) <br>
    - [Send an object from one activity to another by intent](https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents) <br>
    - [Android Studio Parcelable](https://developer.android.com/reference/android/os/Parcelable) <br>
    - [Retrieve image path form assets folder](https://stackoverflow.com/questions/25053716/how-to-get-image-from-android-asset/25054318) <br>
    - [Navigation 2 parents point to 1 child activity](https://stackoverflow.com/questions/19893342/how-to-implement-up-navigation-in-android-for-2-parents-that-point-to-1-child-ac/20145765) <br>
    - [Camera X in Java](https://www.youtube.com/watch?v=IrwhjDtpIU0&ab_channel=CodingReel) <br>
    - [Camera X - Implement a preview](https://developer.android.com/training/camerax/preview) <br>
    - [Camera X - Image Capture](https://developer.android.com/training/camerax/take-photo) <br>
    - [How to request multiple permissions in android studio](https://www.youtube.com/watch?v=nkayHRT8D_w&ab_channel=Foxandroid) <br>
    - [ImageCapture.OutputFileOptions.Builder](https://developer.android.com/reference/androidx/camera/core/ImageCapture.OutputFileOptions.Builder)<br>
    - [Content Provider](https://developer.android.com/guide/topics/providers/content-provider-basics) <br>
    - [Access media files from shared storage](https://developer.android.google.cn/training/data-storage/shared/media?hl=en#java) <br>
    - [Environment.getexternalstoragedirectory() deprecated in API level 29 java](https://stackoverflow.com/questions/57116335/environment-getexternalstoragedirectory-deprecated-in-api-level-29-java) <br>
    - [How do I rotate bitmap in Android](https://stackoverflow.com/questions/29982528/how-do-i-rotate-a-bitmap-in-android) <br>
    - [Android media store tutorial](https://www.androiddevelopersolutions.com/2015/12/android-media-store-tutorial-list-all.html) <br>
    - [Java convert Date to String](https://www.javatpoint.com/java-date-to-string) <br>
    - [Date format](https://developer.android.com/reference/java/text/DateFormat) <br>
    - [Running Android tasks in background thread](https://www.youtube.com/watch?v=IVFWC0rwfL4&ab_channel=CodingPursuits) <br>
    - [Creating a thread in Java](https://www.javatpoint.com/how-to-create-a-thread-in-java) <br>
    - <a href="https://developer.android.com/reference/android/os/Handler#sendMessage(android.os.Message)"> Handler object</a> <br>
    - [Programmatically relaunch/ recreate an activity](https://stackoverflow.com/questions/2486934/programmatically-relaunch-recreate-an-activity)<br>
    - [RecyclerView - Android Studio Tutorial Part 1](https://www.youtube.com/watch?v=18VcnYN5_LM&t=606s&ab_channel=Stevdza-San) <br>
    - [RecyclerView - Android Studio Tutorial Part 2](https://www.youtube.com/watch?v=18VcnYN5_LM&t=606s&ab_channel=Stevdza-San)<br>

<h6>In-app displayed plant's information sources</h6>
    - [12 Best Bell Pepper Benefits + Nutritional Facts & Side Effects (stylesatlife.com)](https://stylesatlife.com/articles/bell-pepper-benefits/) <br>
    - [Bell Peppers 101: Nutrition Facts and Health Benefits (healthline.com)](https://www.healthline.com/nutrition/foods/bell-peppers#vitamins-and-minerals) <br>
    - [Herbs Encyclopaedia](https://play.google.com/store/apps/details?id=com.e_steps.herbs&hl=en&gl=US) <br>
    - [9 Impressive Health Benefits of Cabbage (healthline.com)](https://www.healthline.com/nutrition/benefits-of-cabbage#TOC_TITLE_HDR_9) <br>
    - [12 Proven Health Benefits & Uses of Cabbage | Organic Facts](https://www.organicfacts.net/health-benefits/vegetable/health-benefits-of-cabbage.html) <br>
    - [Cabbage Nutrition Facts and Health Benefits (verywellfit.com)](https://www.verywellfit.com/cabbage-nutrition-facts-calories-and-health-benefit-4117541#toc-health-benefits) <br>
    - [10 Health Benefits of Carrots – Carotene](https://www.carotene.org/10-health-benefits-carrots/) <br>
    - [Carrot Nutrition Facts and Health Benefits (verywellfit.com)](https://www.verywellfit.com/calories-in-carrots-3495643) <br>
    - [Organic facts Health Benefits](https://www.organicfacts.net/health-benefits/vegetable/health-benefits-of-lettuce.html) <br>
    - [Lettuce: Health Benefits, Nutrients, Preparation, and More (webmd.com)](https://www.webmd.com/diet/health-benefits-lettuce) <br>
    - [Health benefits of basil](https://www.medicalnewstoday.com/articles/266425) <br>
    - [24 Fresh Basil Recipes](https://insanelygoodrecipes.com/basil-recipes/) <br>
    - [Everything you need to know  about rosemary](https://www.medicalnewstoday.com/articles/266370) <br>
    - [Calories in fresh rosemary](https://www.nutritionix.com/food/fresh-rosemary) <br>
    - [What are the health benefits of oregano?](https://www.medicalnewstoday.com/articles/266259#nutrition) <br>
    - [Is mint good for you](https://www.medicalnewstoday.com/articles/275944#_noHeaderPrefixedContent) <br>
    - [Cowbane](https://luontoportti.com/en/t/228/cowbane) <br> 
    - [Hemlock](https://luontoportti.com/en/t/1448/hemlock) <br>
    - [Poisonous mushrooms](https://www.arktisetaromit.fi/en/mushrooms/poisonous%20mushrooms/) <br>
    - [Strawberries 101: Nutrition Facts and Health Benefits](https://www.healthline.com/nutrition/foods/strawberries) <br>
    - [Strawberry  recipes](https://www.allrecipes.com/recipes/1110/fruits-and-vegetables/fruits/berries/strawberries/) <br>
    - [Nutritional benefits of the strawberry](https://www.webmd.com/diet/features/nutritional-benefits-of-the-strawberry)  <br>
    - [Blueberries](https://blueberry.org/health-benefits/nutrition-facts/)  <br>
    - [Health benefits of pineapple](https://www.webmd.com/food-recipes/benefits-pineapple) <br>
    - [19  ways  to add pineapple to your dinner](https://www.delish.com/cooking/g1124/savory-pineapple-recipes/) <br>
    - [10 Incredible Health Benefits of Bananas](https://isha.sadhguru.org/us/en/blog/article/10-incredible-health-benefits-of-bananas?gclid=Cj0KCQiAxoiQBhCRARIsAPsvo-wMO_LuA8dfp7D1RP8hfqDvJXOlYVQ9-l0iHBRLhd-Q-5DwfqLIdBcaArp6EALw_wcB) <br>
    - [25 Recipes for Ripe and Overripe](https://www.forksoverknives.com/recipes/vegan-menus-collections/banana-recipes-ways-to-use-ripe-overripe-bananas/) <br>
    - [The health benefits of mango](https://www.bbcgoodfood.com/howto/guide/health-benefits-mango) <br>

<h2>Herb Reference Guide App 's UI</h2>
![Herb_UI_version2](/uploads/b7291fb5384ad26812f57c760f4b301a/Herb_UI_version2.png)

<h2>UML Diagram</h2>
![Herb_Refercence_Guide_Project](/uploads/eea8cf947bdfc3b4f5128b5eb80b872f/Herb_Refercence_Guide_Project.jpg)

<h2>Application Video</h2>
<iframe width="560" height="315" src="https://www.youtube.com/embed/7WMcCnOmJG4" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>


