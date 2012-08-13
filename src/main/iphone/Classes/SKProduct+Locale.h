//
//  SKProduct+Locale.h
//  Worthvader
//
//  Created by Alex Winston on 12/15/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <StoreKit/StoreKit.h>


@interface SKProduct (Locale)
@property (nonatomic, readonly) NSString *priceAsString;
@end
