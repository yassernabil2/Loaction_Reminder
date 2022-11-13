# Loaction_Reminder
This app allows the user to register and login for creating a list with location reminders that remind him to do something when he is at a specific location.

# User Authentication
## -Login:
Create a Login screen to ask users to login using an email address or a Google account. Upon successful login, navigate the user to the Reminders screen. If there is no account, the app should navigate to a Registration (Signup) screen.
## -Signup:
Create a Registration screen to allow a user to register using an email address or a Google account.
## -Authentication:
Enable the authentication using Firebase console and include Firebase UI dependency in the project. You must use this library, FirebaseUI-Android, to implement the authentication.
## -Logout:
Make sure that the users can log out of the app and when the app starts again they are required to login first.
# Map View
## -Map view:
Create a Map view that shows the user's current location.
It should first ask the user's location access permission to show his/her current location on the map.
Ensure that the location access is handled in cases of user denial that the user is shown the right error messages.
## -POI:
The app should ask the user to select a point of interest (POI) on the map to create a reminder.
    -The app asks the user to select a location or POI on the map and add a new marker at that location.
    -The selected POI can be just a simple POI or an area or specific latitude and longitude. The POI must have a location name.
    -Upon saving, the selected location is returned to the Save Reminder page and the user is asked to input the title and description for the reminder.
    -When the reminder is saved, a geofencing request is created. Allowing the user to take Circular Radius for the geofence is a bonus.
## -Compatibility:
The app should work on all the different Android versions including Android Q.
## -Map Styling:
Update the Map Styling using the map styling wizard to generate a nice looking map.
-Users should have the option to change map type from the toolbar items.
## -Geofencing:
When the user enters a geofence, a reminder is retrieved from the local storage and a notification showing the reminder title will appear, even if the app is not open.
# Reminders
## Create Reminders:
Create a screen to add a new reminder when a user reaches the selected location.
Each new reminder should include
title
description
selected location
Important: Capture the user-entered data using live data and data binding.
Use RemindersLocalRepository to save the reminder to the local DB, and create the geofencing request after confirmation.
Reminder data should be saved to local storage.
## Reminders list view:
Create a screen that displays the reminders retrieved from local storage. If there are no reminders, display a "No Data" indicator. If there are any errors, display an error message.
User should be able to navigate from the Reminders list view screen to another screen to create a new reminder.
Each reminder should include
title
description
selected location
## Reminder notification:
For each reminder, create a geofencing request in the background that fires up a notification when the user enters the geofencing area.
Display details about a reminder when a selected POI is reached and the user clicked on the notification.
When the user clicks a notification, a new screen appears to display the reminder details.
