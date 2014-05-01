#import "MixPanelPlugin.h"
#import "Mixpanel.h"
#import "platform/log.h"

@implementation MixPanelPlugin

// The plugin must call super dealloc.
- (void) dealloc {
	[super dealloc];
}

// The plugin must call super init.
- (id) init {
	self = [super init];
	if (!self) {
		return nil;
	}

	return self;
}

- (void) initializeWithManifest:(NSDictionary *)manifest appDelegate:(TeaLeafAppDelegate *)appDelegate {
	@try {
		NSDictionary *ios = [manifest valueForKey:@"ios"];
		NSString *token = [ios valueForKey:@"mixpanelToken"];

		[Mixpanel sharedInstanceWithToken:token];

		NSLOG(@"{mixpanel} Initialized with manifest mixpanelToken: '%@'", token);
	}
	@catch (NSException *exception) {
		NSLOG(@"{mixpanel} Failure to get ios:mixpanelToken from manifest file: %@", exception);
	}
}

- (void) track:(NSDictionary *)jsonObject {
	@try {
		NSString *eventName = [jsonObject valueForKey:@"eventName"];
		
		NSDictionary *evtParams = [jsonObject objectForKey:@"params"];
		if (!evtParams || [evtParams count] <= 0) {
	        [[Mixpanel sharedInstance] track:eventName properties:@{}];

			NSLOG(@"{mixpanel} Delivered event '%@'", eventName);
		} else {
	        [[Mixpanel sharedInstance] track:eventName properties:evtParams];

			NSLOG(@"{mixpanel} Delivered event '%@' with %d params", eventName, (int)[evtParams count]);
		}
	}
	@catch (NSException *exception) {
		NSLOG(@"{mixpanel} Exception while processing event: ", exception);
	}
}

- (void) setIdentity:(NSDictionary *)jsonObject {
	@try {
		NSString *value = [jsonObject valueForKey:@"value"];

		[[Mixpanel sharedInstance] identify:value];

		NSLOG(@"{mixpanel} Set identity to '%@'", value);
	}
	@catch (NSException *exception) {
		NSLOG(@"{mixpanel} Exception while processing event: ", exception);
	}
}

- (void) setGlobalProperty:(NSDictionary *)jsonObject {
	@try {
		NSString *name = [jsonObject valueForKey:@"name"];
		id value = [jsonObject valueForKey:@"value"];
		
		[[Mixpanel sharedInstance] registerSuperProperties:@{name: value}];

		NSLOG(@"{mixpanel} Set super property named '%@' to '%@'", name, value);
	}
	@catch (NSException *exception) {
		NSLOG(@"{mixpanel} Exception while processing event: ", exception);
	}
}

@end

