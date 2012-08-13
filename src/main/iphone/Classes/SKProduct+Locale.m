//
//  SKProduct+Locale.m
//  Worthvader
//
//  Created by Alex Winston on 12/15/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "SKProduct+Locale.h"


@implementation SKProduct (Locale)

- (NSString *) priceAsString
{
	NSNumberFormatter *formatter = [[NSNumberFormatter alloc] init];
	[formatter setFormatterBehavior:NSNumberFormatterBehavior10_4];
	[formatter setNumberStyle:NSNumberFormatterCurrencyStyle];
	[formatter setLocale:[self priceLocale]];
	
	NSString *str = [formatter stringFromNumber:[self price]];
	[formatter release];
	return str;
}

@end
