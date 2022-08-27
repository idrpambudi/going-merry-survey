# Going Merry Survey API



## Getting Started
1. Download the [survey dataset](https://drive.google.com/drive/u/1/folders/1Cvap7cwpbw0fUjQOaOY5ZnW_Mw6UzwoJ) and put in the root directory as `survey_dataset.json`.
2. Clean the dataset. Execute `$ bash clean_dataset.sh`. However, the cleaned dataset already provided in the root directory already.
3. Setup MongoDB. The 2nd step and the next can be achieved by using [MongoDB Compass](https://www.mongodb.com/products/compass) interactively and intuitively.
   1. Install MongoDB server locally. Can be achieved by using dockerized app. `$ docker run --name mongodb -d -p 27017:27017 mongo`.
   2. Install `mongosh` command. Can be seen [here](https://www.mongodb.com/docs/mongodb-shell/install/).
   3. Create database and collection. Execute `$ mongosh < mongodb_setup.js`.
   4. Install MongoDB database tools. Can be seen [here](https://www.mongodb.com/docs/database-tools/installation/installation/). Used for importing data from JSON to a database collection.
   5. Migrate the dataset to `survey` collection. Execute `$ mongoimport --db going-merry --collection survey --file survey_dataset.json --jsonArray`.
4. Run the application. Execute `./gradlew build; ./gradlew bootRun`. Note: If permission denied encountered upon running the application, try `chmod +x gradlew`.

## Contract
### List Surveys
**GET `localhost:8080/survey`**

Request Parameters:
- sort (String): any field name to be sorted in ascending order
- **...filter parameters**

### Get Survey
**GET `localhost:8080/survey/<id>`**

Request Parameters:
- fields (String): comma separated field names to select which fields to be shown in the response

### Aggregate Surveys
**GET `localhost:8080/survey/count`**

Request Parameters:
- **...filter parameters**

### Filter Parameters
- timestamp (String)
- timestampOp (String): override the exact match operator for **timestamp** field filter. Must be one of (eq, lt, gt, lte, gte)
- salary (Integer)
- salaryOp (String): override the exact match operator for **salary** field filter. Must be one of (eq, lt, gt, lte, gte)
- ageRange (String)
- industry (String)
- jobTitle (String)
- currency (String)
- location (String)
- experienceRange (String)
- jobContext (String)
- otherCurrency (String)

## Data Cleansing Difference
- Rename survey fields with simpler terms.
- Reformat timestamps as ISO 8601 format.
- Convert salary into integer format. Rows with complex information in salary column such as informing bonus are kept as is. 98% of salary data is converted into integer.

## Why MongoDB?
- MongoDB has flexible schema and accept dynamic data type in a single field.
- Filter and sort are supported and executed efficiently.
- Filtering a column with multiple data type is operated based on the input filter data type.

## What Can Be Improved
- Add database index to hasten the query latency.
- Better cleaning algorithm for salary field to accomodate the 2%.
- Separate the location for more filtering option.