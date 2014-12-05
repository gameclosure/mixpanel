# Game Closure DevKit Plugin: Mixpanel

This plugin adds analytics support for the
[Mixpanel service](http://mixpanel.com) for Android and iOS platforms.

## Installation
Install the module using the standard devkit install process:

~~~
devkit install https://github.com/gameclosure/mixpanel#v2.0.0
~~~

## Setup

Create a new app in the Mixpanel dashboard and add your app token to your
`manifest.json` file under the `ios` or `android` section as necessary.

~~~
    "android": {
        "mixpanelToken": "YOUR_TOKEN"
    },
~~~

~~~
    "ios": {
        "mixpanelToken": "YOUR_TOKEN"
    },
~~~

## Usage

To use Mixpanel in your game, import the mixpanel object:

~~~
import mixpanel;
~~~

Then send individual track events like this:

~~~
mixpanel.track("myEvent", {
    "score": 999,
    "coins": 11,
    "isRandomParameter": true
});
~~~

If you assign unique ids to your users you can send that on to mixpanel
so it can keep track of users in the same way you are with the `setUserId`
(or `setIdentity`) function.

~~~
mixpanel.setUserId(userId);
~~~

Mixpanel also supports tracking global properties for a user across all
of their events (called [super
properties](https://mixpanel.com/help/reference/javascript#super-properties)
on mixpanel). You can set these using the `setGlobalProperty` function.

~~~
mixpanel.setGlobalProperty(propertyName, propertyValue);
~~~


## Testing

To test for successful integration, build your game:

~~~
devkit debug native-android --clean --open
~~~

Then watch logcat:

~~~
adb logcat | grep mixpanel
~~~

If Mixpanel is hooked up properly, you'll see something like this:

~~~
D/JS      (30374): 5 Dec 13:01:47 LOG console LOG mixpanel track:  testevent {"event_data1":1,"event_data2":"yay"}
E/JS      (30374): {mixpanel} track - success: testevent
~~~

You can test for successful integration on the [Mixpanel website](http://mixpanel.com).

## Platform-specific notes

### Browsers

Nothing actually gets sent to Mixpanel in browsers, but you'll still see logs that look like this:

~~~
LOG mixpanel track:  testevent {"event_data1":1,"event_data2":"yay"} 
~~~
