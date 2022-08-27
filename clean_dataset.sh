#!/bin/bash

filename=survey_dataset.json

echo 'Start cleaning dataset...'

# Rename fields
echo 'Renaming fields'
sed -i -E 's/"Timestamp"/"timestamp"/g' $filename
sed -i -E 's/"How old are you\?"/"ageRange"/g' $filename
sed -i -E 's/"What industry do you work in\?"/"industry"/g' $filename
sed -i -E 's/"Job title"/"jobTitle"/g' $filename
sed -i -E 's/"What is your annual salary\?"/"salary"/g' $filename
sed -i -E 's/"Please indicate the currency"/"currency"/g' $filename
sed -i -E 's/"Where are you located\? \(City\/state\/country\)"/"location"/g' $filename
sed -i -E 's/"How many years of post-college professional work experience do you have\?"/"experienceRange"/g' $filename
sed -i -E 's/"If your job title needs additional context, please clarify here:"/"jobContext"/g' $filename
sed -i -E 's/"If \\\"Other,\\\" please indicate the currency here:"/"otherCurrency"/g' $filename

# Change timestamp format into ISO 8601
echo 'Change timestamp format'
sed -i -E 's/([0-9]+)\/([0-9]+)\/([0-9]+) ([0-9]+:[0-9]+:[0-9]+)/\3-\1-\2T\4\.000Z/g' $filename
sed -i -E 's/"timestamp": "([0-9]+)-([0-9])-([0-9]+)T/"timestamp": "\1-0\2-\3T/g' $filename
sed -i -E 's/"timestamp": "([0-9]+)-([0-9]+)-([0-9])T/"timestamp": "\1-\2-0\3T/g' $filename

# Cleanse salary information into integer
echo 'Convert salary data to integer'
## remove "around" prefix
sed -i -E 's/"salary": "[aA]round(.*)", "currency/"salary": "\1", "currency/g' $filename
## remove "annually" suffix
sed -i -E 's/"salary": "(.*) annually", "currency/"salary": "\1", "currency/g' $filename
## remove "/year" suffix
sed -i -E 's/"salary": "(.*) ?\/? ?([yY]ear|yr)", "currency/"salary": "\1", "currency/g' $filename
## remove trailing decimal
sed -i -E 's/"salary": "(.*)[\.,][0-9]{1,2}", "currency/"salary": "\1", "currency/g' $filename
## remove comma separator and symbol
sed -i -E 's/"salary": "~?[^0-9]?([0-9]*)[,\. ]*([0-9]*)[,\. ]*([0-9]+)"/"salary": "\1\2\3"/g' $filename
## replace K suffix with trailing decimal with 000
sed -i -E 's/"salary": "[^0-9]?([0-9]+)\.([0-9]) ?[kK]"/"salary": "\1\200"/g' $filename
## replace K suffix with 000
sed -i -E 's/"salary": "[^0-9]?([0-9]+) ?[kK]"/"salary": "\1000"/g' $filename
## convert to integer
sed -i -E 's/"salary": "([0-9]+)"/"salary": \1/g' $filename

echo 'Clean dataset finished'