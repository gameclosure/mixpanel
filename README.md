# Game Closure DevKit Plugin: MixPanel

This plugin adds advertising support from the [MixPanel service](http://mixpanel.com) for Android and iOS platforms.

## Usage

Install the addon with `basil install mixpanel`.

Include it in the `manifest.json` file under the "addons" section for your game:

~~~
"addons": [
	"mixpanel"
],
~~~

Under the Android/iOS sections, you can configure the MixPanel plugin:

~~~
	"android": {
		"versionCode": 1,
		"icons": {
			"36": "resources/icons/android36.png",
			"48": "resources/icons/android48.png",
			"72": "resources/icons/android72.png",
			"96": "resources/icons/android96.png"
		},
		"mixPanelToken": "YOUR_TOKEN"
	},
~~~

~~~
	"ios": {
		"bundleID": "mmp",
		"appleID": "568975017",
		"version": "1.0.3",
		"icons": {
			"57": "resources/images/promo/icon57.png",
			"72": "resources/images/promo/icon72.png",
			"114": "resources/images/promo/icon114.png",
			"144": "resources/images/promo/icon144.png"
		},
		"mixPanelToken": "YOUR_TOKEN"
	},
~~~

Note that the key names are case-sensitive.

You can test for successful integration on the [MixPanel website](http://mixpanel.com).

