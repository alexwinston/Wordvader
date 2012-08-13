//
//  WordvaderDatastore.m
//  Wordvader
//
//  Created by Alex Winston on 12/14/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "WordvaderDatastore.h"
#import "Settings.h"

#import "ASIFormDataRequest.h"


@implementation WordvaderDatastore

+ (WordvaderDatastore *)datastore
{	
	return [[[self alloc] init] autorelease];
}

- (id)init
{
    if((self = [super init])) {
        // Set up instance variables and whatever else here
    }
    return self;
}

- (void)postDownloadReceipt {
	ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:
								   [NSURL URLWithString:[kAWWordvaderDomainURL stringByAppendingString:@"rs/receipt"]]];
	[request setPostValue:[[UIDevice currentDevice] uniqueIdentifier] forKey:@"udid"];
	[request setPostValue:@"download" forKey:@"receipt"];
	[request startAsynchronous];
}

- (NSInteger)numberOfHintsAvailable {
	NSUserDefaults *prefs = [NSUserDefaults standardUserDefaults];
	
	// Give the user 5 hints when the application is downloaded
	if ([prefs objectForKey:@"AWWordvaderNumberOfHints"] == nil) {		
		[prefs setInteger:5 forKey:@"AWWordvaderNumberOfHints"];
		[prefs synchronize];
		
		// Post the download receipt
		[self postDownloadReceipt];
	}
	
    return 100;
//	return [prefs integerForKey:@"AWWordvaderNumberOfHints"];
}

- (void)usedHint {
	NSUserDefaults *prefs = [NSUserDefaults standardUserDefaults];
	
	[prefs setInteger:[prefs integerForKey:@"AWWordvaderNumberOfHints"] - 1
			   forKey:@"AWWordvaderNumberOfHints"];
	[prefs synchronize];
}

- (void)purchasedNumberOfHints:(NSInteger)numberOfHints {
	NSUserDefaults *prefs = [NSUserDefaults standardUserDefaults];
	
	[prefs setInteger:[prefs integerForKey:@"AWWordvaderNumberOfHints"] + numberOfHints
			   forKey:@"AWWordvaderNumberOfHints"];
	[prefs synchronize];
}

@end
