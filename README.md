# Android Test


## MOB-01 - TV Shows home screen

As a user I want to see a list of existent TV Shows. I should be able to save or delete a TV Show as favorite.

### Acceptance Criteria

- This screen should be the first when the App opens
- This screen should look like the layout [attached](https://bitbucket.org/ac-recruitment/android-challenge/src/master/assets/Android Challenge.png)
- When **_Shows_** tab is selected then the TV Shows list should be shown `(/shows?page=:num)`
- When **_Favorites_** tab (MOB-03) is selected then my favorites shows should be listed.
- The favorites list needs to be accessed offline. You can choose how to save the TV Show.
- The list item should have:
	- Poster `{image.medium}`
	- Name `{name}`
	- Button
- The poster image have to be cached
- The button should save or delete the TV Show as favorite
	- When the TV Show is **not** saved, then the save icon ([ic_save](https://bitbucket.org/ac-recruitment/android-challenge/src/master/assets/ic_save.svg)) should be used
	- When the TV Show is **saved** , then the remove icon ([ic_remove](https://bitbucket.org/ac-recruitment/android-challenge/src/master/assets/ic_remove.svg)) should be used
- When deleting a saved TV Show, a confirmation dialog should be shown to confirm deletion.
- When the user click on the row then the details screen (MOB-02) should be opened
- When any user action fails, then a retry dialog should be shown. The retry dialog should have:
	- Title:
		- Oops, something went wrong
	- Description:
		- For saving/removing favorite:
		- There was a problem saving/deleting this TV Show. Do you want to try again?
	- For timeout/no connection:
		- An error occurred while fetching data. Do you want to try again?
	- Cancel option
	- Retry option



## MOB-02 - Details Screen

As a user I want to see the details information of existent TV Shows and then I should be able to remove or add the TV Show to favorite list.

### Acceptance Criteria

- This screen should have the same identity as the list screen (MOB-01), but the layout is up to you
- The screen title should be the TV Show's name
- The screen should have:
	- Poster `{image.large}`
	- Summary `{summary}`
	- IMDb `{externals.imdb}`
- When the user clicks on IMDb link, then the TV Show page `(https://www.imdb.com/title/:id)` should be opened in the default browser app
- When there is **no** imdb id, then the link should not be visible
- The Toolbar should have an action item to save or delete the TV show from favorites.
	- When the TV Show is **not** saved, then the save icon ([ic_save](https://bitbucket.org/ac-recruitment/android-challenge/src/master/assets/ic_save.svg))) should be used
	- When the TV Show is **saved** , then the remove icon ([ic_remove](https://bitbucket.org/ac-recruitment/android-challenge/src/master/assets/ic_remove.svg))a should be used


## MOB-03 - Favorites Screen

As a user I want to see all the TV Shows I marked as favorites, having the option to delete them from my favorites.

### Acceptance Criteria

- This screen should look like the TV Shows screen
- This screen should have all the user's saved TV Shows
- User needs to have the option to delete a TV Show from favorites
- When deleting a TV Show, a popup needs to be shown to confirm deletion
- After deletion, TV Show needs to be removed from Favorites List and from the persistence
- When the user click on the row then the details screen (MOB-02) should be opened


## Technical Details:

* **Language:** Java or Kotlin
* **Persistence:** Any type of persistence can be used
* **Tests:** Unit tests are important
* **Documentation:** `http://www.tvmaze.com/api`
* **API call example:** `http://api.tvmaze.com/shows?page=1`

### Notes

* You can find all the assets [here](https://bitbucket.org/ac-recruitment/android-challenge/src/master/assets/)
* This assessment must be delivered within 2 days.
* You can use whatever third party library you want to accomplish these requirements.
* You must provide a COMMENTS.txt (plain text) or a COMMENTS.md (Markdown) file at the root of your repository, explaining:

    * Main architecture decisions you've made and a quick explanation of why.
    * List of third party libraries and why you chose each one.
    * What in your code could be improved if you had more time.
    * Mention anything that was asked but not delivered and why, and any additional comments.
  
* Any questions, please send an email to **recruitment.engineering@avenuecode.com**

### Delivery Instructions

1. You must provide his BitBucket username. A free BitBucket account can be created at http://bitbucket.org
1. The recruiter will give you read permission to a repository named **android-challenge**, at https://bitbucket.org/ac-recruitment/android-challenge
1. You must fork this repository into a private repository on your own account and push your code in there.
1. Once finished, you must give the user **ac-recruitment** read permission on your repository so that you can be evaluated. Then, please contact back your recruiter and he will get an engineer to evaluate your test.
1. After you are evaluated, the recruiter will remove your read permission from the original repository.

Thank you,  
The AvenueCode Recruiting Team