# Strategy Games

## How to play
The code for these games was written for some strategy games we played at MSJ Math Club lectures, based on a minievent from the Stanford Math Tournament. Feel free to use them for your own event!

To play, make a copy of these slides and update them as needed to display for everyone: https://tinyurl.com/StrategySlides

You will need some way to take in responses from players. I used Google Forms, which worked great, but you can use whatever you'd like (e.g. Menti) and update the form link on the 2nd slide. If you're using Google Forms, you can open up the responses on a Google spreadsheet and copy and paste responses from the spreadsheet to wherever you're running the code.

The instructions are listed on the slides; essentially, everyone will submit their own response to a game, and they will play everyone else, like a free-for-all or round robin. Everyone is given a certain amount of time to submit (I've included timers on each slide, but feel free to change them up as you see fit). Once time is over, run the code for the corresponding game, and copy and paste the responses you've received into the console (or wherever it's running). Then write "done" on the next line, and the code will run and spit out the results.

There are a couple things to look out for in the input, such as names that start with "done" as the first word. Some game-specific instructions are given below along with the explanations:

## Games

### 2/3 of the Average
The name of the game describes it pretty well: everyone must enter a number from 1 to 1000, possibly a decimal, and whoever is closest to 2/3 of the average wins. 

This game is related to game theory: if all players were "perfectly rational", they'd reach the Nash equilibrium of guessing 1. But of course, this doesn't happen in reality; rather it demonstrates players' "depth of reasoning," essentially how many levels of thinking they will go before stopping.

### Ambitious Auction
In this game, all players will have 100 coins to bid on 10 items, where the 1st item is worth 10 points, the 2nd worth 20, and so on. Players can split up their bids however they want as long as they don't exceed the 100 coin limit, and if they don't spend all their coins, any unbid coins are each worth 0.1 points. The highest bidder receives the points for that item, and if there are ties, the points are evenly split among all top bidders. 

There are a couple different prominent strategies here: one is to think that multiple people will go for the 100-point item, and possibly on the 90-point item too, and so on, and so you try to guess which is the highest item that will only have 1 bid, and you bid all 100 coins on it. Another strategy is to go for the lower point items, and try to get multiple of those by splitting up your coins.

For the input, don't worry if people use more than 100 coins, or if they enter more or less than 10 bids. Entries using more than 100 coins will be discarded, entries with more than 10 bids will only have the first 10 used, and entries with less than 10 bids will default to bidding 0 coins on the later items.

### Fireball Frenzy
In this game, players play a modified version of the game fireball. Everyone starts with a power of 1, and each charge adds 1 to their power. A fireball with power > 0 will beat a charge, and with power > 2 will beat a shield. A fireball with a higher power will beat one with a lower power. Players enter 10 moves, and when paired with another player, will go head to head until one gets fireballed (or they end up drawing). Winner is the one with the best record among all.

There are many different strategies here, such as constantly fireballing with low power, or maybe throwing in shields here and there. One strategy that may trump the others is to use a power > 2 fireball on the last move, because that would break through both lower power fireballs and shields.

For the input, make sure no one enters a name that starts with 5 C's, F's, or S's, as their name would then be used as the string of moves.

### Pizza Party
In this game, everyone enters a number of pizza slices they want (possibly a decimal), from a total of 100 possible slices. The goal is to get as many pizza slices as possible for yourself, but if everyone collectively wants too many slices, then the most greedy people are knocked off until the number of slices is back within 100.

This game relies on quite a bit of game sense, such as seeing how many people are playing (which players may be able to figure out based on the winning score from the Fireball game if they're attentive) and how risky the people are (which may be intuited from the 2/3 Average game). Based on that, participants can feel out how many slices is right.


