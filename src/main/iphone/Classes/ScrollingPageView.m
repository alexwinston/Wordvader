//
//  ScrollingPage.m
//  Scrolling
//
//  Created by Alex Winston on 12/11/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "ScrollingPageView.h"


@implementation ScrollingPageView

- (void)setPageIndex:(int)thePageIndex
{
	if (thePageIndex >= 0)
		pageIndex = thePageIndex;
}

- (int)getPageIndex
{
	return pageIndex;
}

- (void)incrementPageIndex
{
	pageIndex++;
}

- (void)decrementPageIndex
{
	if (pageIndex - 1 >= 0)
		pageIndex--;
}

- (void)observeValueForKeyPath:(NSString *)keyPath 
                      ofObject:(id)object 
                        change:(NSDictionary *)change 
                       context:(void *)context {
    [self performSelector:(SEL)context withObject:change];
}

- (void)updateAlpha:(NSDictionary *)change {
	CGFloat offset = [[change objectForKey:NSKeyValueChangeNewKey] CGPointValue].x;
    CGFloat origin = [self frame].origin.x;
    CGFloat delta = fabs(origin - offset);
	
    [UIView beginAnimations:@"Fading" context:nil];
    if (delta < [self frame].size.width) {
        self.alpha = 1 - delta/self.frame.size.width*0.7;
    } else {
        self.alpha = 0.3;
    }
    [UIView commitAnimations];
}

@end
