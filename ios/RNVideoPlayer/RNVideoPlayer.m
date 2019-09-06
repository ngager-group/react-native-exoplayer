//
//  RNVideoPlayer.m
//  RNVideoPlayer
//
//  Created by DC5 Admin (MACMINI032) on 10/25/16.
//  Copyright © 2016 DC5 Admin (MACMINI032). All rights reserved.
//

#import "RNVideoPlayer.h"

@implementation RNVideoPlayer

{
    NSMutableArray<RCTResponseSenderBlock> *_callbacks;
    RCTPromiseResolveBlock resolver;
    RCTPromiseRejectBlock rejecter;
};

@synthesize bridge = _bridge;

RCT_EXPORT_MODULE();

- (NSArray<NSString *> *)supportedEvents {
    return @[@"onEndReached"];
}

RCT_EXPORT_METHOD(showVideoPlayer: (NSString*) url
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    
   
    self.videoURL = [NSURL URLWithString:url];
    //AppDelegate *delegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
    UIWindow *window = [[UIApplication sharedApplication] keyWindow];

    AVPlayer *player = [AVPlayer playerWithURL:self.videoURL];
    self.playerViewController = [AVPlayerViewController new];
    _playerViewController.player = player;
    _playerViewController.showsPlaybackControls = YES;
    
    [[NSNotificationCenter defaultCenter] addObserver:self
     
                                            selector:@selector(playerItemDidReachEnd:)
                                            name:AVPlayerItemDidPlayToEndTimeNotification
                                            object:[_playerViewController.player currentItem]];

  
    resolver = resolve;
    rejecter = reject;
    
  //  _callbacks = [NSMutableArray new];
 //   [_callbacks addObject:callback];
    
//    callback(@[[NSNull null],body:@{ @"close": @true}]);
   // callback(@[[NSNull null], @true]);

    dispatch_async(dispatch_get_main_queue(), ^{

        [window.rootViewController.view addSubview:self.playerViewController.view];
        [window.rootViewController presentViewController:self.playerViewController animated:YES completion:nil];

    });

    _playerViewController.player.play;
}

- (void)playerItemDidReachEnd:(NSNotification *)notification {
    
    
    [[NSNotificationCenter defaultCenter] removeObserver:self
                                                    name:AVPlayerItemDidPlayToEndTimeNotification
                                                  object:notification.object];
   
    
    resolver(@"success");
   // rejecter(@"errorr",@"messaage",@"errorrrlast");
     NSLog(@"IT REACHED THE END");
    
   // rejecter = nil;
   // resolver = nil;
    
//    UIAlertView *alert = [[UIAlertView alloc]
//                          initWithTitle:@"Make an informed choice"
//                          message:nil
//                          delegate:self
//                          cancelButtonTitle:@"Cancel"
//                          otherButtonTitles:@"OK", nil];
//    [alert show];
    
   // [self sendEventWithName:@"onEndReached" body:@{ @"close": @true}];

//  [self sendEventWithName:@"onEndReached" body:@"Hello"];
  
    
   // RCTResponseSenderBlock callback = _callbacks[0];
   // callback(@[@true]);
    //Remove the callback
   // [_callbacks removeObjectAtIndex:0];
}


@end
