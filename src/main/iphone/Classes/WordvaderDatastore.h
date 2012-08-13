//
//  WordvaderDatastore.h
//  Wordvader
//
//  Created by Alex Winston on 12/14/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//


@interface WordvaderDatastore : NSObject {
}

+ (WordvaderDatastore *)datastore;

- (NSInteger)numberOfHintsAvailable;
- (void)usedHint;
- (void)purchasedNumberOfHints:(NSInteger)numberOfHints;
@end
