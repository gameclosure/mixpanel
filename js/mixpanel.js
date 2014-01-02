var hasNativeEvents = NATIVE && NATIVE.plugins && NATIVE.plugins.sendEvent;

var MixPanel = Class(function () {
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

	this.setIdentity = function (value) {
		if (hasNativeEvents) {
			NATIVE.plugins.sendEvent("MixPanelPlugin", "setIdentity", JSON.stringify({
					value: value
				}));
		}
	}

	this.setGlobalProperty = function (name, value) {
		if (hasNativeEvents) {
			NATIVE.plugins.sendEvent("MixPanelPlugin", "setGlobalProperty", JSON.stringify({
					name: name,
					value: value
				}));
		}
	}
});

exports = new MixPanel();

