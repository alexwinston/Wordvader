//
//  WordvaderAppDelegate.m
//  Wordvader
//
//  Created by Alex Winston on 12/8/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import "WordvaderAppDelegate.h"


@implementation WordvaderAppDelegate

@synthesize window;
@synthesize navigationController;


#pragma mark -
#pragma mark Lifecycle methods

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {    
    // Override point for customization after app launch    
	
	[window addSubview:[navigationController view]];
    [window makeKeyAndVisible];
	return YES;
}


- (void)applicationWillTerminate:(UIApplication *)application {
	// Save data if appropriate
}


#pragma mark -
#pragma mark Memory management

- (void)dealloc {
	[navigationController release];
	[window release];
	[super dealloc];
}


@end

