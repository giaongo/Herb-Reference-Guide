## Herb Reference Guide  App
This app has been developed within the scope of Mobile course belonging to Metropolia Unviersity Of Applied Sciences.

### Developers and Maintainers
1. Giao Ngo 
1. Tai Nguyen 
1. Shayne Kandagor 

### Project description
The app is an Android standalone herb reference book that provides plenty of information about nutrition value, health benefits, warnings and food  suggestion of different types of plant to users. Besides that, there is area for users to add notes, update and delete their notes for future reference.

To enrich user's  interaction and experience with the app, we added a custom-built camera with the help Android's Camera X Api, and created a specialised gallery for storing plant's images. The purpose of having camera and gallery is for those who would like to capture every images of plant they find interesting on the spot/at that moment and collect those images to the collection for keepsake.

### In-app technology
*  Coding language: Java
*  Min sdk : 21, target sdk: 32

###  User target
1. The app targets at Android users 
1. For healthy eaters who are curious of the benefits of specific plant items 
1. For adventurous travellers (mountain hikers, bikers, campers, forest explorers) to know  which plants are edible and which are poisonous  before touching/eating them 
1. For patients seeking for natural remedies and for medical students.

### Installation of the app
1. Download the our application apk file called herb_reference_guide.apk
1. Our application logo can be recognized with green leaves logo and our application name is Herb Reference Guide.

### Document test case and Javadoc

1. [Document Test Case](https://docs.google.com/document/d/1dYyVx79O6v3mLDUfCLSHCA7NKpzyc5xV/edit?usp=sharing&ouid=114237886261926611764&rtpof=true&sd=true)
1. [Javadoc](https://users.metropolia.fi/~phatn/HerbReferenceGuide/)

### Study sources and code references

**Layout and UI components**

1. [RecyclerView & RecyclerView Adapter](https://www.youtube.com/watch?v=18VcnYN5_LM&ab_channel=Stevdza-San)
1. [RecyclerView click listener (best performance)](https://www.youtube.com/watch?v=7GPUpvcU1FE&ab_channel=PracticalCoding) 
1. [Adding Card View with a textbox](https://developer.android.com/guide/topics/ui/layout/cardview)
1. [Floating Action Button](https://developer.android.com/guide/topics/ui/floating-action-button) 
1. [Set menu on toolbar and menu item click listener](https://stackoverflow.com/questions/7479992/handling-a-menu-item-click-event-android) 
1. [App Launch Icon](https://stock.adobe.com/search/images?filters%5Bcontent_type%3Azip_vector%5D=1&filters%5Borientation%5D=vertical%2Csquare&filters%5Borientation_type%5D%5Bis_square%5D=true&filters%5Bcontent_type%3Aimage%5D=1&order=relevance&safe_search=1&limit=100&search_page=1&search_type=filter-select&acp=&aco=herbal+icon&k=herbal+icon&get_facets=1&asset_id=275377040) 
1. [Snackbar](https://material.io/components/snackbars/android#theming-snackbars) 

**Fonts, Icon and Image sources used in app**

1. Font: HeadlandOne-Regular by Google Fonts designed by Gary Lonergan. And Serif Monospace by Android.
1. Icons : Unlimited usage of icons without author's permission and attribution. [PublicDomain Vectors.com](https://publicdomainvectors.org/)
1. Images: Unlimited  usage of images without author's permission and attribution: 
    1. [Pixabay](https://pixabay.com/)
    1. [Unsplash](https://unsplash.com/)
    1. [Pexels](https://www.pexels.com/)

**Backend**

1. [Prepopulate Room Database](https://www.youtube.com/watch?v=pe28WeQ0VCc&ab_channel=Stevdza-San)
1. [Launching an activity for result](https://developer.android.com/training/basics/intents/result) 
1. [Send an object from one activity to another by intent](https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents) 
1. [Android Studio Parcelable](https://developer.android.com/reference/android/os/Parcelable) 
1. [Retrieve image path form assets folder](https://stackoverflow.com/questions/25053716/how-to-get-image-from-android-asset/25054318) 
1. [Navigation 2 parents point to 1 child activity](https://stackoverflow.com/questions/19893342/how-to-implement-up-navigation-in-android-for-2-parents-that-point-to-1-child-ac/20145765) 
1. [Camera X in Java](https://www.youtube.com/watch?v=IrwhjDtpIU0&ab_channel=CodingReel) 
1. [Camera X - Implement a preview](https://developer.android.com/training/camerax/preview) 
1. [Camera X - Image Capture](https://developer.android.com/training/camerax/take-photo) 
1. [How to request multiple permissions in android studio](https://www.youtube.com/watch?v=nkayHRT8D_w&ab_channel=Foxandroid) 
1. [ImageCapture.OutputFileOptions.Builder](https://developer.android.com/reference/androidx/camera/core/ImageCapture.OutputFileOptions.Builder)
1. [Content Provider](https://developer.android.com/guide/topics/providers/content-provider-basics) 
1. [Access media files from shared storage](https://developer.android.google.cn/training/data-storage/shared/media?hl=en#java) 
1. [Environment.getexternalstoragedirectory() deprecated in API level 29 java](https://stackoverflow.com/questions/57116335/environment-getexternalstoragedirectory-deprecated-in-api-level-29-java) 
1. [How do I rotate bitmap in Android](https://stackoverflow.com/questions/29982528/how-do-i-rotate-a-bitmap-in-android) 
1. [Android media store tutorial](https://www.androiddevelopersolutions.com/2015/12/android-media-store-tutorial-list-all.html) 
1. [Java convert Date to String](https://www.javatpoint.com/java-date-to-string) 
1. [Date format](https://developer.android.com/reference/java/text/DateFormat)
1. [Running Android tasks in background thread](https://www.youtube.com/watch?v=IVFWC0rwfL4&ab_channel=CodingPursuits) 
1. [Creating a thread in Java](https://www.javatpoint.com/how-to-create-a-thread-in-java)
1. [Handler object](https://developer.android.com/reference/android/os/Handler#sendMessage(android.os.Message))
1. [Programmatically relaunch/ recreate an activity](https://stackoverflow.com/questions/2486934/programmatically-relaunch-recreate-an-activity)
1. [RecyclerView - Android Studio Tutorial Part 1](https://www.youtube.com/watch?v=18VcnYN5_LM&t=606s&ab_channel=Stevdza-San)
1. [RecyclerView - Android Studio Tutorial Part 2](https://www.youtube.com/watch?v=xgpLYwEmlO0&ab_channel=Stevdza-San)

**In-app displayed plant's information sources**

1. [12 Best Bell Pepper Benefits + Nutritional Facts & Side Effects (stylesatlife.com)](https://stylesatlife.com/articles/bell-pepper-benefits/)
1. [Bell Peppers 101: Nutrition Facts and Health Benefits (healthline.com)](https://www.healthline.com/nutrition/foods/bell-peppers#vitamins-and-minerals) 
1. [Herbs Encyclopaedia](https://play.google.com/store/apps/details?id=com.e_steps.herbs&hl=en&gl=US)
1. [9 Impressive Health Benefits of Cabbage (healthline.com)](https://www.healthline.com/nutrition/benefits-of-cabbage#TOC_TITLE_HDR_9)
1. [12 Proven Health Benefits & Uses of Cabbage | Organic Facts](https://www.organicfacts.net/health-benefits/vegetable/health-benefits-of-cabbage.html)
1. [Cabbage Nutrition Facts and Health Benefits (verywellfit.com)](https://www.verywellfit.com/cabbage-nutrition-facts-calories-and-health-benefit-4117541#toc-health-benefits)
1. [10 Health Benefits of Carrots – Carotene](https://www.carotene.org/10-health-benefits-carrots/)
1. [Carrot Nutrition Facts and Health Benefits (verywellfit.com)](https://www.verywellfit.com/calories-in-carrots-3495643)
1. [Organic facts Health Benefits](https://www.organicfacts.net/health-benefits/vegetable/health-benefits-of-lettuce.html) 
1. [Lettuce: Health Benefits, Nutrients, Preparation, and More (webmd.com)](https://www.webmd.com/diet/health-benefits-lettuce) 
1. [Health benefits of basil](https://www.medicalnewstoday.com/articles/266425)
1. [24 Fresh Basil Recipes](https://insanelygoodrecipes.com/basil-recipes/) 
1. [Everything you need to know  about rosemary](https://www.medicalnewstoday.com/articles/266370) 
1. [Calories in fresh rosemary](https://www.nutritionix.com/food/fresh-rosemary) 
1. [What are the health benefits of oregano?](https://www.medicalnewstoday.com/articles/266259#nutrition)
1. [Is mint good for you](https://www.medicalnewstoday.com/articles/275944#_noHeaderPrefixedContent) 
1. [Cowbane](https://luontoportti.com/en/t/228/cowbane) 
1. [Hemlock](https://luontoportti.com/en/t/1448/hemlock) 
1. [Poisonous mushrooms](https://www.arktisetaromit.fi/en/mushrooms/poisonous%20mushrooms/) 
1. [Strawberries 101: Nutrition Facts and Health Benefits](https://www.healthline.com/nutrition/foods/strawberries)
1. [Strawberry  recipes](https://www.allrecipes.com/recipes/1110/fruits-and-vegetables/fruits/berries/strawberries/) 
1. [Nutritional benefits of the strawberry](https://www.webmd.com/diet/features/nutritional-benefits-of-the-strawberry) 
1. [Blueberries](https://blueberry.org/health-benefits/nutrition-facts/)  
1. [Health benefits of pineapple](https://www.webmd.com/food-recipes/benefits-pineapple) 
1. [19  ways  to add pineapple to your dinner](https://www.delish.com/cooking/g1124/savory-pineapple-recipes/) 
1. [10 Incredible Health Benefits of Bananas](https://isha.sadhguru.org/us/en/blog/article/10-incredible-health-benefits-of-bananas?gclid=Cj0KCQiAxoiQBhCRARIsAPsvo-wMO_LuA8dfp7D1RP8hfqDvJXOlYVQ9-l0iHBRLhd-Q-5DwfqLIdBcaArp6EALw_wcB)
1. [25 Recipes for Ripe and Overripe](https://www.forksoverknives.com/recipes/vegan-menus-collections/banana-recipes-ways-to-use-ripe-overripe-bananas/) 
1. [The health benefits of mango](https://www.bbcgoodfood.com/howto/guide/health-benefits-mango)

### Herb Reference Guide App 's UI

![Herb_UI_version2](/uploads/b7291fb5384ad26812f57c760f4b301a/Herb_UI_version2.png)

### UML Diagram

![Herb_Refercence_Guide_Project](/uploads/e3d31d98273e11d4c9506d0acaf6dabd/Herb_Refercence_Guide_Project.jpg)

### Herb Reference Guide Demo Video

<div align="center">

[![Herb Reference Guide Video](http://img.youtube.com/vi/7WMcCnOmJG4/0.jpg)](http://www.youtube.com/watch?v=7WMcCnOmJG4 "Herb Reference Guide Video")

</div>
