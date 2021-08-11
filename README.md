[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/ggbbest/ggbwallet)

# GgbWallet - Advanced, Open Source Ethereum Mobile Wallet & dApp Browser for Android

[![Build Status](https://api.travis-ci.com/GgbWallet/alpha-wallet-android.svg?branch=master)](https://api.travis-ci.com/GgbWallet/alpha-wallet-android.svg?branch=master)
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg )](https://github.com/ggbbest/ggbwallet/graphs/commit-activity)
![GitHub contributors](https://img.shields.io/github/contributors/GgbWallet/alpha-wallet-android.svg)
[![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/ggbbest/ggbwallet/blob/master/LICENSE) based networks.
[![codecov](https://codecov.io/gh/GgbWallet/alpha-wallet-android/branch/master/graph/badge.svg)](https://codecov.io/gh/GgbWallet/alpha-wallet-android)

아래 주소에서 클론 하여 수정함
https://github.com/AlphaWallet/alpha-wallet-android


## About GgbWallet - Features

Easy to use and secure open source Ethereum wallet for Android and iOS, with native ERC20, ERC721 and ERC875 support. GgbWallet supports all Ethereum based networks: Ethereum, xDai, Ethereum Classic, Artis, POA, Ropsten, Goerli, Kovan, Rinkeby and Sokol.

- Beginner Friendly
- Secure Enclave Security
- Web3 dApp Browser
- TokenScript Enabled
- Interact with DeFi, DAO and Games with SmartTokens
- No hidden fees or tech background needed

### GgbWallet Is A Token Wallet

GgbWallet's focus is to provide an interface to interact with Ethereum Tokens in an intuitive, simple and full featured manner. This is what sets us aside from other open source ethereum wallets.


# Getting Started

1. [Download](https://developer.android.com/studio/) Android Studio.
1. Clone this repository
1. Run `./gradlew build` to install tools and dependencies.

Find more information in our available [documentation](https://github.com/ggbbest/ggbwallet/blob/master/docs/overview.md).

### Add your token to GgbWallet

If you’d like to include TokenScript and extend your token functionalities, please refer to [TokenScript](https://github.com/GgbWallet/TokenScript).

### Add dApp to the “Discover dApps” section in the browser

Submit a PR to the following file:
https://github.com/ggbbest/ggbwallet/blob/master/app/src/main/assets/dapps_list.json

## How to Contribute

You can submit feedback and report bugs as Github issues. Please be sure to include your operating system, device, version number, and steps to reproduce reported bugs.

## How to customise the appearance of your wallet fork

If you are forking GgbWallet, and have a token that you want to be locked visible this can now be done easily. Let's say we want to only show Ethereum Mainnet, and always show the USDC stablecoin.

```
class CustomViewSettings
{
```
...
```
    private static final List<TokenInfo> lockedTokens = Arrays.asList(
            // new TokenInfo(String TokenAddress, String TokenName, String TokenSymbol, int TokenDecimals, boolean isEnabled, int ChainId)
            new TokenInfo("0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48", "USD Coin", "USDC", 6, true, EthereumNetworkBase.MAINNET_ID)
    );
    
    private static final List<Integer> lockedChains = Arrays.asList(
            EthereumNetworkBase.MAINNET_ID
    );
```
Further, you may have your own Dapp that sells or uses the USDC that you want your users to use.
```
public static boolean minimiseBrowserURLBar() { return true; } //this removes the ability to enter URL's directly (they can be clicked on within your dapp)
```
```
public abstract class EthereumNetworkBase ...
{
    private static final String DEFAULT_HOMEPAGE = "https://my-awesome-nfts.com/usdc/";
```
If you are forking GgbWallet and you have a cool Token, please consider donating a small amount of said Token to `ggbwallet.eth` to help fund continuing development of the main repo.


To learn more about us, please check our Blog or join the conversation:
- [Telegram](https://t.me/ggbbest)

## Contributors
Thank you to all the contributors! You are awesome.

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->


<!-- markdownlint-enable -->
<!-- prettier-ignore-end -->
<!-- ALL-CONTRIBUTORS-LIST:END -->
## License
GgbWallet Android is available under the [MIT license](https://github.com/ggbbest/ggbwallet/blob/master/LICENSE). Free for commercial and non-commercial use.
