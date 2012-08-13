//
//  ScrollingPage.h
//  Scrolling
//
//  Created by Alex Winston on 12/11/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface ScrollingPageView : UIButton {
	int pageIndex;
}

- (void)setPageIndex:(int)thePageIndex;
- (int)getPageIndex;
- (void)incrementPageIndex;
- (void)decrementPageIndex;

@end
