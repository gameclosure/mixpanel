var hasNativeEvents = NATIVE && NATIVE.plugins && NATIVE.plugins.sendEvent;

var MixPanel = Class(function () {
	this.trackEvent =
	this.track = function (name, data) {
		if (DEBUG) {
			logger.log("track: ", name, JSON.stringify(data));
		}

		if (hasNativeEvents) {
			NATIVE.plugins.sendEvent("MixPanelPlugin", "track", JSON.stringify({
					eventName: name,
					params: data
				}));
		}
	};

	this.setUserId =
	this.setIdentity = function (value) {
		if (DEBUG) {
			logger.log("setIdentity: ", value);
		}

		if (hasNativeEvents) {
			NATIVE.plugins.sendEvent("MixPanelPlugin", "setIdentity", JSON.stringify({
					value: value
				}));
		}
	}

	this.setGlobalProperty = function (name, value) {
		if (DEBUG) {
			logger.log("setGlobalProperty: ", name, value);
		}

		if (hasNativeEvents) {
			NATIVE.plugins.sendEvent("MixPanelPlugin", "setGlobalProperty", JSON.stringify({
					name: name,
					value: value
				}));
		}
	}
});

exports = new MixPanel();

