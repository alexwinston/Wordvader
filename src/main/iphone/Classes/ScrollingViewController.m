//
//  ScrollingViewController.m
//  Scrolling
//
//  Created by David Janes on 09-09-25.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <QuartzCore/QuartzCore.h>
#import <StoreKit/StoreKit.h>

#import "Settings.h"
#import "ScrollingViewController.h"
#import "ScrollingPageView.h"
#import "DetailViewController.h"

#import "ASIFormDataRequest.h"
#import "JSON.h"

#import "SKProduct+Locale.h"
#import "UIImage+Scale.h"

#define kPurchaseProductIdentifier1 @"com.alexwinston.wordvader_01"
#define kPurchaseProductIdentifier2 @"com.alexwinston.wordvader_02"
#define kPurchaseProductIdentifier3 @"com.alexwinston.wordvader_03"


@implementation ScrollingViewController
@synthesize closeFlippedViewButton;
@synthesize pickImageButton;
@synthesize scrollingView, scrollView;
@synthesize pageControl;
@synthesize instructionsLabel;

#pragma mark -
#pragma mark Controller lifecycle

- (void)viewDidLoad 
{
	[super viewDidLoad];
	
	wordvaderDatastore = [[WordvaderDatastore datastore] retain];
	
	purchaseCounts = [[NSMutableDictionary dictionary] retain];
	purchaseButtonTitles = [[NSMutableArray array] retain];
	pageViews = [[NSMutableArray array] retain];
	
	// Configure the activity indicator
	CGRect frame = CGRectMake(0.0, 0.0, 25.0, 25.0);
	pickImageActivityIndicator = [[UIActivityIndicatorView alloc] initWithFrame:frame];
	[pickImageActivityIndicator sizeToFit];
	pickImageActivityIndicator.autoresizingMask = (UIViewAutoresizingFlexibleLeftMargin |
												   UIViewAutoresizingFlexibleRightMargin |
												   UIViewAutoresizingFlexibleTopMargin |
												   UIViewAutoresizingFlexibleBottomMargin);
	
	pickImageActivityButton = [[UIBarButtonItem alloc] initWithCustomView:pickImageActivityIndicator];
	pickImageActivityButton.target = self;
	
	// Enable the activity indicator until the products are loaded
	if ([SKPaymentQueue canMakePayments])
		[self enableActivityIndicator];
	
	// Request the in-app products
	NSSet *productIdentifiers = [NSSet setWithObjects:kPurchaseProductIdentifier1, kPurchaseProductIdentifier2, kPurchaseProductIdentifier3, nil];
	SKProductsRequest *request = [[SKProductsRequest alloc] initWithProductIdentifiers:productIdentifiers];
	[request setDelegate:self];
	[request start];
	
	scrollView.delegate = self;
	scrollView.indicatorStyle = UIScrollViewIndicatorStyleWhite;
	scrollView.clipsToBounds = NO;
	scrollView.scrollsToTop = NO;
	scrollView.scrollEnabled = YES;
	scrollView.pagingEnabled = YES;
	scrollView.canCancelContentTouches = NO;
	self.navigationItem.leftBarButtonItem = nil;

	[self setupPage];
}


- (void)didReceiveMemoryWarning 
{
    [super didReceiveMemoryWarning];
}

- (void)viewDidUnload 
{
}


- (void)dealloc 
{
	[wordvaderDatastore release];
	[purchaseCounts release];
	[purchaseButtonTitles release];
	[pageViews release];
	[currentFlippedViewController release];
	[currentFlippedImageView release];
	
    [super dealloc];
}

#pragma mark -
#pragma mark Controller methods

- (void)setupPage
{
	[self updateNumberOfHints];
	
	// Load the existing solution pages
	NSUserDefaults *prefs = [NSUserDefaults standardUserDefaults];
	
	// Reverse the order of the existing solutions so they are inserted in the correct order
	for (NSDictionary *solutionDictionary in [[prefs arrayForKey:@"AWWordvaderSolutions"] reverseObjectEnumerator]) {
		// insertPage
		NSString *imagePath = [NSHomeDirectory() stringByAppendingPathComponent:[solutionDictionary valueForKey:@"AWWordvaderImage"]];
		NSData *imageData = [NSData dataWithContentsOfFile:imagePath];
		
		[self insertPage:[[UIImage imageWithData:imageData] imageByScalingProportionallyToSize:CGSizeMake(208, 300)]
				animated:NO];
	}

}

- (void)postPurchaseReceipt:(NSString *)productIdentifier
{
	ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:
								   [NSURL URLWithString:[kAWWordvaderDomainURL stringByAppendingString:@"rs/receipt"]]];
	[request setPostValue:[[UIDevice currentDevice] uniqueIdentifier] forKey:@"udid"];
	[request setPostValue:[NSString stringWithFormat:@"purchase/%@",productIdentifier]
			   forKey:@"receipt"];
	[request startAsynchronous];
}

- (void)removeSolutionAtIndex:(int)index
{
	NSUserDefaults *prefs = [NSUserDefaults standardUserDefaults];
	
	// Remove the copy of the image
	NSDictionary *solutionDictionary = [[prefs arrayForKey:@"AWWordvaderSolutions"] objectAtIndex:index];
	NSString *imagePath = [NSHomeDirectory() stringByAppendingPathComponent:[solutionDictionary valueForKey:@"AWWordvaderImage"]];
	
	NSFileManager *fm = [NSFileManager defaultManager];
	[fm removeItemAtPath:imagePath error:nil];
	
	// Remove the solution for the index
	[[prefs mutableArrayValueForKey:@"AWWordvaderSolutions"] removeObjectAtIndex:index];
	[prefs synchronize];
}

- (void)updateNumberOfHints
{
	self.navigationItem.title = [NSString stringWithFormat:@"%i Hint%@ Remaining", [wordvaderDatastore numberOfHintsAvailable], [wordvaderDatastore numberOfHintsAvailable] != 1 ? @"s" : @""];
}

- (void)enableActivityIndicator
{
	self.navigationItem.rightBarButtonItem = pickImageActivityButton;
	[pickImageActivityIndicator startAnimating];
}

- (void)disableActivityIndicator
{
	self.navigationItem.rightBarButtonItem = pickImageButton;
	[pickImageActivityIndicator stopAnimating];
}

- (UIImage *)imageFromUIView:(UIView *)theView
{
	UIGraphicsBeginImageContext(theView.frame.size);
	[theView.layer renderInContext:UIGraphicsGetCurrentContext()];
	UIImage *image = UIGraphicsGetImageFromCurrentImageContext();
	UIGraphicsEndImageContext();
	
	return image;
}


#pragma mark -
#pragma mark UIAlertView delegate methods

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex {
	if (alertView.title == @"Purchase Hints") {
		// Enable the activity indicator
		[self enableActivityIndicator];
		
		if (buttonIndex == 0) {
			SKPayment *payment = [SKPayment paymentWithProductIdentifier:kPurchaseProductIdentifier1];
			[[SKPaymentQueue defaultQueue] addPayment:payment];
		} else if (buttonIndex == 1) {
			SKPayment *payment = [SKPayment paymentWithProductIdentifier:kPurchaseProductIdentifier2];
			[[SKPaymentQueue defaultQueue] addPayment:payment];
		} else if (buttonIndex == 2) {
			SKPayment *payment = [SKPayment paymentWithProductIdentifier:kPurchaseProductIdentifier3];
			[[SKPaymentQueue defaultQueue] addPayment:payment];
		} else {
			// Canceled purchase
			[self disableActivityIndicator];
		}
	} else {
		// Disable the activity indicator after an alert
		[self disableActivityIndicator];
	}
}


#pragma mark -
#pragma mark SKProductsRequest delegate methods
- (void)productsRequest:(SKProductsRequest *)request didReceiveResponse:(SKProductsResponse *)response
{
	for (SKProduct *product in response.products) {
		[purchaseButtonTitles addObject:[NSString stringWithFormat:@"%@ for %@",product.localizedTitle, product.priceAsString]];
	}
	
	// Add the transaction observer to handle purchases
	[[SKPaymentQueue defaultQueue] addTransactionObserver:self];

	// Disable the activity indicator
	[self disableActivityIndicator];
}

#pragma mark -
#pragma mark SKPaymentTransactionObserver delegate methods

- (void)paymentQueue:(SKPaymentQueue *)queue updatedTransactions:(NSArray *)transactions
{
	for (SKPaymentTransaction *transaction in transactions)
    {
        switch (transaction.transactionState)
        {
            case SKPaymentTransactionStatePurchased:
                [self completeTransaction:transaction];
                break;
            case SKPaymentTransactionStateFailed:
                [self failedTransaction:transaction];
                break;
            case SKPaymentTransactionStateRestored:
                //[self restoreTransaction:transaction];
            default:
                break;
        }
    }
}

- (void)completeTransaction:(SKPaymentTransaction *)transaction
{
	// TODO Grab the number of hints from the title
	if ([transaction.payment.productIdentifier isEqual:kPurchaseProductIdentifier1])
		[wordvaderDatastore purchasedNumberOfHints:50];
	else if ([transaction.payment.productIdentifier isEqual:kPurchaseProductIdentifier2])
		[wordvaderDatastore purchasedNumberOfHints:150];
	else if ([transaction.payment.productIdentifier isEqual:kPurchaseProductIdentifier3])
		[wordvaderDatastore purchasedNumberOfHints:500];
	
	[self updateNumberOfHints];
	
	// Remove the transaction from the payment queue.
    [[SKPaymentQueue defaultQueue] finishTransaction: transaction];
	
	// Post the purchase receipt
	[self postPurchaseReceipt:transaction.payment.productIdentifier];
	
	// Disable the activity indicator
	[self disableActivityIndicator];
}

- (void) failedTransaction:(SKPaymentTransaction *)transaction
{
	NSLog(@"failedTransaction");
    if (transaction.error.code != SKErrorPaymentCancelled) {
		// Display request failed alert
		UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Error"
														message:@"There was an error with your purchase."
													   delegate:self
											  cancelButtonTitle:@"OK"
											  otherButtonTitles:nil];
		[alert show];
		[alert release];
    } else {
		[self disableActivityIndicator];
		NSLog(@"Transaction cancelled");
	}
	
    [[SKPaymentQueue defaultQueue] finishTransaction: transaction];
}


#pragma mark -
#pragma mark UIImagePickerControllerDelegate methods

- (IBAction)pickImage:(id)sender {
	if ([wordvaderDatastore numberOfHintsAvailable] <= 0) {
		if ([SKPaymentQueue canMakePayments]) {
			// Display purchase hints alert
			UIAlertView *alert = [[UIAlertView alloc] init];
			[alert setTitle:@"Purchase Hints"];
			[alert setMessage:@"You do not have any more hints. Please pick the number of hints you would like to purchase."];
			[alert setDelegate:self];
			for (NSString *purchaseButtonTitle in purchaseButtonTitles)
				[alert addButtonWithTitle:purchaseButtonTitle];
			[alert addButtonWithTitle:@"Cancel"];
			[alert setCancelButtonIndex:[purchaseButtonTitles count]];
			
			[alert show];
			[alert release];
		} else {
			// Alert user payments are disabled
			UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Payments Disabled"
															message:@"Payments have been disabled on this device."
														   delegate:self
												  cancelButtonTitle:@"OK"
												  otherButtonTitles:nil];
			[alert show];
			[alert release];
		}
	} else {
		// Set up the image picker controller and add it to the view
		UIImagePickerController *imagePickerController = [[UIImagePickerController alloc] init];
		imagePickerController.delegate = self;
		imagePickerController.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
		
		[self presentModalViewController:imagePickerController animated:YES];
	}
}

- (void)imagePickerController:(UIImagePickerController *)picker 
		didFinishPickingImage:(UIImage *)image
				  editingInfo:(NSDictionary *)editingInfo
{
	// Generate a uuid to identify the new solution
	NSString *uuid = [[NSProcessInfo processInfo] globallyUniqueString];
	
	// Start the activity indicator
	[self enableActivityIndicator];
	
	// Save a copy of the UIImage and a PNG to the home directory using the uuid
	NSString *imagePath = [NSHomeDirectory() stringByAppendingPathComponent:[NSString stringWithFormat:@"Documents/%@.png", uuid]];
	NSData *imageData = UIImagePNGRepresentation(image);
	[imageData writeToFile:imagePath atomically:YES];
	
	// Add the solution to the user defaults
	NSUserDefaults *prefs = [NSUserDefaults standardUserDefaults];
	if ([prefs arrayForKey:@"AWWordvaderSolutions"] == nil)
		[prefs setObject:[NSMutableArray array] forKey:@"AWWordvaderSolutions"];
	
	NSMutableDictionary *solutionDictionary = [NSMutableDictionary dictionary];
	[solutionDictionary setValue:[NSString stringWithFormat:@"Documents/%@.png", uuid] forKey:@"AWWordvaderImage"];
	
	[[prefs mutableArrayValueForKey:@"AWWordvaderSolutions"] insertObject:solutionDictionary atIndex:0];
	[prefs synchronize];
	
	// Post the game screenshot to be solved
	ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:
								   [NSURL URLWithString:[kAWWordvaderDomainURL stringByAppendingString:@"rs/solve"]]];
	[request setTimeOutSeconds:30];
	[request setNumberOfTimesToRetryOnTimeout:1];
	[request setPostValue:[[UIDevice currentDevice] uniqueIdentifier] forKey:@"udid"];
	[request setData:imageData forKey:@"image"];
	[request setDelegate:self];
	[request startAsynchronous];
	
	// Dismiss the image picker
	[picker dismissModalViewControllerAnimated:YES];
}

- (void)requestFinished:(ASIHTTPRequest *)request
{
	NSString *response = [request responseString];
	//NSLog(@"%@", response);
	
	// Alert user hint didn't return any words
	NSDictionary *jsonDictionary = [response JSONValue];
	if ([[jsonDictionary objectForKey:@"hintGroups"] count] == 0) {
		UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"No Words Found"
														message:@"No words were found for the game screenshot supplied. A hint will not be charged.\n\n If this appears to be a mistake please contact support."
													   delegate:self
											  cancelButtonTitle:@"OK"
											  otherButtonTitles:nil];
		[alert show];
		[alert release];
		
		// Remove solution from AWWordvaderSolutions at index 0 when there is an error
		[self removeSolutionAtIndex:0];
		
		return;
	}
	
	// Insert the new solution into the users preferences
	NSUserDefaults *prefs = [NSUserDefaults standardUserDefaults];
	
	[wordvaderDatastore usedHint];
	[self updateNumberOfHints];
	
	NSMutableDictionary *solutionDictionary = [[[prefs arrayForKey:@"AWWordvaderSolutions"] objectAtIndex:0] mutableCopy];
	[solutionDictionary setObject:jsonDictionary forKey:@"AWWordvaderSolution"];
	
	[[prefs mutableArrayValueForKey:@"AWWordvaderSolutions"] replaceObjectAtIndex:0
																		withObject:solutionDictionary];
	[prefs synchronize];

	// Scroll to the first page before inserting a new page
	[self changePageWithNumber:0];
	
	// insertPage
	NSString *imagePath = [NSHomeDirectory() stringByAppendingPathComponent:[solutionDictionary valueForKey:@"AWWordvaderImage"]];
	NSData *imageData = [NSData dataWithContentsOfFile:imagePath];
	
	[self insertPage:[[UIImage imageWithData:imageData] imageByScalingProportionallyToSize:CGSizeMake(208, 300)]
			animated:YES];
	
	[solutionDictionary release];
	
	// Disable the activity indicator
	[self disableActivityIndicator];
}

- (void)requestFailed:(ASIHTTPRequest *)request
{
	// Display request failed alert
	UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Error"
													message:@"There was an error connecting to the Wordvader server"
												   delegate:self
										  cancelButtonTitle:@"OK"
										  otherButtonTitles:nil];
	[alert show];
	[alert release];
	
	NSError *error = [request error];
	NSLog(@"%@", error);
	
	// Remove solution from AWWordvaderSolutions at index 0 when there is an error
	[self removeSolutionAtIndex:0];
}

- (void)imagePickerControllerDidCancel:(UIImagePickerController *)picker
{
	// Dismiss the image picker
	[picker dismissModalViewControllerAnimated:YES];
}


#pragma mark -
#pragma mark UIScrollViewDelegate methods

- (void)scrollViewDidScroll:(UIScrollView *)_scrollView
{
	if (pageControlIsChangingPage) {
        return;
    }

	// Switch page at 50% across
    CGFloat pageWidth = _scrollView.frame.size.width;
    int page = floor((_scrollView.contentOffset.x - pageWidth / 2) / pageWidth) + 1;
    pageControl.currentPage = page;
}

- (void)scrollViewDidEndDecelerating:(UIScrollView *)_scrollView 
{
    pageControlIsChangingPage = NO;
}

#pragma mark -
#pragma mark UIPageControl delegate methods

- (void)changePageWithNumber:(NSInteger)pageNumber
{	
	// Scroll the scroll view when the page control is clicked
    CGRect frame = scrollView.frame;
    frame.origin.x = frame.size.width * pageNumber;
    frame.origin.y = 0;
	
    [scrollView scrollRectToVisible:frame animated:YES];
}

- (IBAction)changePage:(id)sender 
{
	[self changePageWithNumber:pageControl.currentPage];
	
	// When the animated scrolling finishings, scrollViewDidEndDecelerating will turn this off
    pageControlIsChangingPage = YES;
}

- (IBAction)insertPage //:(id)sender
{
	NSString *imageName = [NSString stringWithFormat:@"image%d.png", pageControl.numberOfPages + 1];
	UIImage *image = [[UIImage imageNamed:imageName] imageByScalingProportionallyToSize:CGSizeMake(208, 300)];

	[self insertPage:image animated:YES];
}

- (void)insertPage:(UIImage *)image animated:(BOOL)animated
{
	ScrollingPageView *pageView = [ScrollingPageView buttonWithType:UIButtonTypeCustom];
	[pageView setClipsToBounds:NO];
	[pageView setAlpha:0.0];
	[pageView setPageIndex:0];
	[pageView setImage:image forState:UIControlStateNormal];
	[pageView addTarget:self action:@selector(touchPage:) forControlEvents:UIControlEventTouchUpInside];
	//[pageView insertSubview:[[UIImageView alloc] initWithImage:[UIImage imageNamed:@"pageshadow.png"]] atIndex:0];
	
	CGRect rect = pageView.frame;
	rect.size.height = image.size.height + 13;
	rect.size.width = image.size.width + 13;
	//rect.origin.x = ((scrollView.frame.size.width - image.size.width) / 2);
	//rect.origin.y = ((scrollView.frame.size.height - image.size.height) / 2);
	pageView.frame = rect;
	
	// Add the delete button on the page view
	UIButton *deleteButton = [UIButton buttonWithType:UIButtonTypeCustom];
	[deleteButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"close.png"]] forState:UIControlStateNormal];
	[deleteButton addTarget:self action:@selector(deletePage:) forControlEvents:UIControlEventTouchUpInside];
	CGRect buttonRect = deleteButton.frame;
	buttonRect.size.width = 23;
	buttonRect.size.height = 23;
	deleteButton.frame = buttonRect;
	[pageView addSubview:deleteButton];
	
	// Insert the new page as a subview
	[pageViews insertObject:pageView atIndex:0];
	[scrollView insertSubview:pageView atIndex:0];
	
	// Shift existing pages
	[UIView beginAnimations:nil context:nil];
	if (!animated) [UIView setAnimationDuration:0];
	[UIView setAnimationDelegate:self];
	[UIView setAnimationDidStopSelector:@selector(insertAnimation:finished:context:)];
	for (int i = 0; i < pageControl.numberOfPages; i++) {
		ScrollingPageView *imageView = [pageViews objectAtIndex:i + 1];
		[imageView incrementPageIndex];
		
		CGRect rect = imageView.frame;
		rect.origin.x = ((scrollView.frame.size.width - imageView.frame.size.width) / 2) + ([imageView getPageIndex] * scrollView.frame.size.width) - 7;
		imageView.frame = rect;
	}
	// Hide the instructions when there are pages
	if (pageControl.numberOfPages == 0)
		instructionsLabel.alpha = 0;
	[UIView commitAnimations];

	
	// Update the page count and scroll view content size
	pageControl.numberOfPages++;
	[scrollView setContentSize:CGSizeMake(pageControl.numberOfPages * scrollView.frame.size.width, scrollView.bounds.size.height)];
	
	// Add offset change observer to fade while scrolling
	[self.scrollView addObserver:pageView 
					  forKeyPath:@"contentOffset" 
						 options:NSKeyValueObservingOptionNew
						 context:@selector(updateAlpha:)];
}
												  
-(void)insertAnimation:(NSString*)id finished:(BOOL)finished context:(void *)context
{
	[UIView beginAnimations:nil context:nil];
	[[pageViews objectAtIndex:0] setAlpha:1.0];
	[UIView commitAnimations];
	
	// NOTE Call update manually otherwise the page control isn't always updated
	[pageControl updateCurrentPageDisplay];
	//NSLog(@"%i %i", pageControl.currentPage, pageControl.numberOfPages);
}

- (void)deletePage:(id)sender
{
	// Remove offset change observer to fade while scrolling
	ScrollingPageView *imageView = [pageViews objectAtIndex:pageControl.currentPage];
	[self.scrollView removeObserver:imageView 
						 forKeyPath:@"contentOffset"];
	
	// Fade out deleted page
	[UIView beginAnimations:nil context:nil];
	[UIView setAnimationDelegate:self];
	[UIView setAnimationDidStopSelector:@selector(deleteAnimation:finished:context:)];
	[imageView setAlpha:0.0];
	[UIView commitAnimations];
	
	// Remove the solutions from the NSUserDefaults
	[self removeSolutionAtIndex:pageControl.currentPage];
}

-(void)deleteAnimation:(NSString*)id finished:(BOOL)finished context:(void *)context
{
	// NOTE: Get a reference to the current page before the animation otherwise it breaks
	ScrollingPageView *pageView = [pageViews objectAtIndex:pageControl.currentPage];
	
	// Shift existing pages
	[UIView beginAnimations:nil context:nil];
	for (int i = pageControl.currentPage + 1; i < pageControl.numberOfPages; i++) {
		ScrollingPageView *imageView = [pageViews objectAtIndex:i];
		[imageView decrementPageIndex];
		
		CGRect rect = imageView.frame;
		rect.origin.x = ((scrollView.frame.size.width - imageView.frame.size.width) / 2) + ([imageView getPageIndex] * scrollView.frame.size.width) - 7;
		imageView.frame = rect;
	}
	
	[pageViews removeObjectAtIndex:pageControl.currentPage];
	
	// Update the page count and scroll view content size
	pageControl.numberOfPages--;
	//NSLog(@"%i %i", pageControl.currentPage, pageControl.numberOfPages);
	[scrollView setContentSize:CGSizeMake(pageControl.numberOfPages * scrollView.frame.size.width, scrollView.bounds.size.height)];
	
	// Show the instructions if there are no pages
	if (pageControl.numberOfPages <= 0)
		instructionsLabel.alpha = 1;
	
	[UIView commitAnimations];
	
	[pageView removeFromSuperview];
}

- (void)touchPage:(id)sender
{
	// Disable pick image button
	self.navigationItem.rightBarButtonItem = nil;
	
	// Get the touched page
	ScrollingPageView *pageView = [pageViews objectAtIndex:pageControl.currentPage];
	[scrollView bringSubviewToFront:pageView];

	// Disable user interactions during page flip animation
	[scrollingView setUserInteractionEnabled:NO];
	
	// TODO release
	currentFlippedViewController = [[DetailViewController alloc] initWithNibName:@"DetailViewController" bundle:nil];
	[currentFlippedViewController setSolutionIndex:pageControl.currentPage];
	
	currentFlippedImageView = [[UIImageView alloc] initWithImage:[self imageFromUIView:currentFlippedViewController.view]];
	currentFlippedImageView.userInteractionEnabled = NO;
	//currentFlippedImageView.contentMode = UIViewContentModeScaleAspectFit;
	CGRect imageViewBounds = currentFlippedImageView.frame;
	imageViewBounds.size.width = pageView.bounds.size.width;
	imageViewBounds.size.height = pageView.bounds.size.height;
	[currentFlippedImageView setFrame:imageViewBounds];
	
	// Animate the page flip and zoom
	[UIView animateWithDuration:0.5
					 animations:^ {
						 [UIView setAnimationTransition:UIViewAnimationTransitionFlipFromRight
												forView:pageView
												  cache:YES];
						 
						 [pageView addSubview:currentFlippedImageView];
					 }
					 completion:^(BOOL finished) {
						 [UIView animateWithDuration:0.5
										  animations:^ {
											  CGRect imageViewBounds = self.view.bounds;
											  // TODO Calculate bounds origin
											  imageViewBounds.origin.x = -44;
											  imageViewBounds.origin.y = -86;
											  [currentFlippedImageView setFrame:imageViewBounds];
										  }
										  completion:^(BOOL finished) {
											  // Insert the flipped view after the flip animations have completed
											  [self.view addSubview:currentFlippedViewController.view];
											  
											  // Enable the back button
											  self.navigationItem.leftBarButtonItem = closeFlippedViewButton;
										  }];
					 }];
}

- (IBAction)closeFlipped:(id)sender
{
	// Disable the back button
	self.navigationItem.leftBarButtonItem = nil;
	
	// Update the image from the current flipped view
	[currentFlippedImageView setImage:[self imageFromUIView:currentFlippedViewController.view]];
	
	[currentFlippedViewController.view removeFromSuperview];
	[currentFlippedViewController release];
	
	ScrollingPageView *pageView = [pageViews objectAtIndex:pageControl.currentPage];
	
	// Zoom out the detail view onto the back of the flipped page
	[UIView beginAnimations:nil context:nil];
	[UIView setAnimationDelegate:self];
	[UIView setAnimationDidStopSelector:@selector(closeFlippedAnimation:finished:context:)];
	
	CGRect imageViewBounds = pageView.frame;
	imageViewBounds.origin.x = 0;
	imageViewBounds.origin.y = 0;
	[currentFlippedImageView setFrame:imageViewBounds];
	
	[UIView setAnimationDuration:0.5];
	[UIView commitAnimations];
}

-(void)closeFlippedAnimation:(NSString*)id finished:(BOOL)finished context:(void *)context {
	ScrollingPageView *pageView = [pageViews objectAtIndex:pageControl.currentPage];
	
	[UIView beginAnimations:nil context:nil];
	[UIView setAnimationDelegate:self];
	[UIView setAnimationDidStopSelector:@selector(closeFlippedAnimationStopped:finished:context:)];
	[UIView setAnimationDuration:0.5];
	[UIView setAnimationTransition:UIViewAnimationTransitionFlipFromLeft
						   forView:pageView
							 cache:YES];
	[currentFlippedImageView removeFromSuperview];
	[UIView commitAnimations];
}

-(void)closeFlippedAnimationStopped:(NSString*)id finished:(BOOL)finished context:(void *)context {
	[currentFlippedImageView release];
	
	// Enable user interaction after page flip animation
	[scrollingView setUserInteractionEnabled:YES];
	
	// Enable pick image button
	self.navigationItem.rightBarButtonItem = pickImageButton;
}

@end
