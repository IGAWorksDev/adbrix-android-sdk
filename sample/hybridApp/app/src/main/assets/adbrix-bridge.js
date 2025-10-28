var adbrixBridge = {
    logEvent: function(eventName, properties){
      if(isAndroidBridgeAvailable()){
        if(properties === null || properties === undefined){
            window.adbrixWebBridge.logEvent(eventName, null);
        } else{
            window.adbrixWebBridge.logEvent(eventName, JSON.stringify(properties));
        }
      }
      if(isIosBridgeAvailable()){
        window.webkit.messageHandlers.adbrixWebBridge.logEvent(eventName, properties);
      }
    },
    enableSDK: function(){
        if(isAndroidBridgeAvailable()){
            window.adbrixWebBridge.enableSDK();
        }
        if(isIosBridgeAvailable()){
            window.webkit.messageHandlers.adbrixWebBridge.enableSDK();
        }
    },
    disableSDK: function(){
        if(isAndroidBridgeAvailable()){
            window.adbrixWebBridge.disableSDK();
        }
        if(isIosBridgeAvailable()){
            window.webkit.messageHandlers.adbrixWebBridge.disableSDK();
      }
    }
};

function isAndroidBridgeAvailable() {
  var result = false;
  try{
    if (window.adbrixWebBridge) {
      result = true;
    }
    if (!result) {
      console.log("Android adbrixWebBridge not found.");
    }
  } catch(err){
    console.log(err);
  }
  return result;
}

function isIosBridgeAvailable(){
  var result = false;
  if(window.webkit
    && window.webkit.messageHandlers
    && window.webkit.messageHandlers.adbrixWebBridge){
    result = true;
  }
  if(!result){
    console.log("iOS adbrixWebBridge not found.");
  }
  return result;
}

class ABEvent {
    static get LOGIN() { return "abx:login"; }
    static get LOGOUT() { return "abx:logout"; }
    static get SIGN_UP() { return "abx:sign_up"; }
    static get USE_CREDIT() { return "abx:use_credit"; }
    static get APP_UPDATE() { return "abx:app_update"; }
    static get INVITE() { return "abx:invite"; }
    static get PURCHASE() { return "abx:purchase"; }
    static get LEVEL_ACHIEVED() { return "abx:level_achieved"; }
    static get TUTORIAL_COMPLETED() { return "abx:tutorial_completed"; }
    static get CHARACTER_CREATED() { return "abx:character_created"; }
    static get STAGE_CLEARED() { return "abx:stage_cleared"; }
    static get REFUND() { return "abx:refund"; }
    static get ADD_TO_CART() { return "abx:add_to_cart"; }
    static get ADD_TO_WISHLIST() { return "abx:add_to_wishlist"; }
    static get PRODUCT_VIEW() { return "abx:product_view"; }
    static get CATEGORY_VIEW() { return "abx:category_view"; }
    static get REVIEW_ORDER() { return "abx:review_order"; }
    static get SEARCH() { return "abx:search"; }
    static get SHARE() { return "abx:share"; }
    static get VIEW_HOME() { return "abx:view_home"; }
    static get LIST_VIEW() { return "abx:list_view"; }
    static get CART_VIEW() { return "abx:cart_view"; }
    static get PAYMENT_INFO_ADDED() { return "abx:paymentinfo_added"; }
}

class ABEventProperty {
    static get IS_SKIP() { return "abx:is_skip"; }
    static get LEVEL() { return "abx:level"; }
    static get STAGE() { return "abx:stage"; }
    static get PREV_VER() { return "abx:prev_ver"; }
    static get CURR_VER() { return "abx:curr_ver"; }
    static get KEYWORD() { return "abx:keyword"; }
    static get SHARING_CHANNEL() { return "abx:sharing_channel"; }
    static get SIGN_CHANNEL() { return "abx:sign_channel"; }
    static get INVITE_CHANNEL() { return "abx:invite_channel"; }
    static get ORDER_ID() { return "abx:order_id"; }
    static get DELIVERY_CHARGE() { return "abx:delivery_charge"; }
    static get PENALTY_CHARGE() { return "abx:penalty_charge"; }
    static get PAYMENT_METHOD() { return "abx:payment_method"; }
    static get ORDER_SALES() { return "abx:order_sales"; }
    static get DISCOUNT() { return "abx:discount"; }
    static get CATEGORY1() { return "abx:category1"; }
    static get CATEGORY2() { return "abx:category2"; }
    static get CATEGORY3() { return "abx:category3"; }
    static get CATEGORY4() { return "abx:category4"; }
    static get CATEGORY5() { return "abx:category5"; }
    static get ITEMS() { return "abx:items"; }
    static get ITEM_PRODUCT_ID() { return "abx:product_id"; }
    static get ITEM_PRODUCT_NAME() { return "abx:product_name"; }
    static get ITEM_PRICE() { return "abx:price"; }
    static get ITEM_QUANTITY() { return "abx:quantity"; }
    static get ITEM_DISCOUNT() { return "abx:discount"; }
    static get ITEM_CURRENCY() { return "abx:currency"; }
    static get ITEM_CATEGORY1() { return "abx:category1"; }
    static get ITEM_CATEGORY2() { return "abx:category2"; }
    static get ITEM_CATEGORY3() { return "abx:category3"; }
    static get ITEM_CATEGORY4() { return "abx:category4"; }
    static get ITEM_CATEGORY5() { return "abx:category5"; }
}

class ABInviteChannel {
    static get KAKAO() { return "Kakao"; }
    static get NAVER() { return "Naver"; }
    static get LINE() { return "Line"; }
    static get GOOGLE() { return "Google"; }
    static get FACEBOOK() { return "Facebook"; }
    static get TWITTER() { return "Twitter"; }
    static get WHATSAPP() { return "whatsApp"; }
    static get QQ() { return "QQ"; }
    static get WECHAT() { return "WeChat"; }
    static get ETC() { return "ETC"; }
}

class ABSharingChannel {
    static get FACEBOOK() { return "Facebook"; }
    static get KAKAOTALK() { return "KakaoTalk"; }
    static get KAKAOSTORY() { return "KakaoStory"; }
    static get LINE() { return "Line"; }
    static get WHATSAPP() { return "whatsApp"; }
    static get QQ() { return "QQ"; }
    static get WECHAT() { return "WeChat"; }
    static get SMS() { return "SMS"; }
    static get EMAIL() { return "Email"; }
    static get COPY_URL() { return "copyUrl"; }
    static get ETC() { return "ETC"; }
}

class ABSignUpChannel {
    static get KAKAO() { return "Kakao"; }
    static get NAVER() { return "Naver"; }
    static get LINE() { return "Line"; }
    static get GOOGLE() { return "Google"; }
    static get FACEBOOK() { return "Facebook"; }
    static get TWITTER() { return "Twitter"; }
    static get WHATSAPP() { return "whatsApp"; }
    static get QQ() { return "QQ"; }
    static get WECHAT() { return "WeChat"; }
    static get USER_ID() { return "UserId"; }
    static get ETC() { return "ETC"; }
    static get SKT_ID() { return "SkTid"; }
    static get APPLE_ID() { return "AppleId"; }
}

class ABPaymentMethod {
    static get CREDIT_CARD() { return "CreditCard"; }
    static get BANK_TRANSFER() { return "BankTransfer"; }
    static get MOBILE_PAYMENT() { return "MobilePayment"; }
    static get ETC() { return "ETC"; }
}

class ABCurrency {
    static get KR() { return "KRW"; }
    static get US() { return "USD"; }
    static get JP() { return "JPY"; }
    static get EU() { return "EUR"; }
    static get UK() { return "GBP"; }
    static get CN() { return "CNY"; }
    static get TW() { return "TWD"; }
    static get HK() { return "HKD"; }
    static get ID() { return "IDR"; } // Indonesia
    static get IN() { return "INR"; } // India
    static get RU() { return "RUB"; } // Russia
    static get TH() { return "THB"; } // Thailand
    static get VN() { return "VND"; } // Vietnam
    static get MY() { return "MYR"; } // Malaysia
}


class ItemBuilder {
  constructor() {
    this.item = {};
  }

  productId(value) {
    this.item['abx:product_id'] = { value, type: 'string' };
    return this;
  }

  productName(value) {
    this.item['abx:product_name'] = { value, type: 'string' };
    return this;
  }

  price(value) {
    this.item['abx:price'] = { value, type: 'double' };
    return this;
  }

  discount(value) {
    this.item['abx:discount'] = { value, type: 'double' };
    return this;
  }

  quantity(value) {
    this.item['abx:quantity'] = { value, type: 'long' };
    return this;
  }

  category1(value) {
    this.item['abx:category1'] = { value, type: 'string' };
    return this;
  }

  category2(value) {
    this.item['abx:category2'] = { value, type: 'string' };
    return this;
  }

  category3(value) {
    this.item['abx:category3'] = { value, type: 'string' };
    return this;
  }

  category4(value) {
    this.item['abx:category4'] = { value, type: 'string' };
    return this;
  }

  category5(value) {
    this.item['abx:category5'] = { value, type: 'string' };
    return this;
  }

  currency(value) {
    this.item['abx:currency'] = { value, type: 'string' };
    return this;
  }

  customString(key, value) {
    this.item[key] = { value, type: 'string' };
    return this;
  }

  customLong(key, value) {
    this.item[key] = { value, type: 'long' };
    return this;
  }

  customDouble(key, value) {
    this.item[key] = { value, type: 'double' };
    return this;
  }

  customBool(key, value) {
    this.item[key] = { value, type: 'boolean' };
    return this;
  }

  build() {
    return this.item;
  }
}

class PropertiesBuilder {
  constructor(initialProperties = {}) {
    this.properties = { ...initialProperties };
  }

  items(itemsInput) {
    let itemsArray;
    if (Array.isArray(itemsInput)) {
      itemsArray = itemsInput;
    } else if (itemsInput && typeof itemsInput === 'object') {
      itemsArray = [itemsInput];
    } else {
      console.warn('[Properties] items() expects an array or single item object');
      return this;
    }

    this.properties['abx:items'] = itemsArray;
    return this;
  }

  orderId(value) {
    this.properties['abx:order_id'] = { value, type: 'string' };
    return this;
  }

  orderSales(value) {
    this.properties['abx:order_sales'] = { value, type: 'double' };
    return this;
  }

  deliveryCharge(value) {
    this.properties['abx:delivery_charge'] = { value, type: 'double' };
    return this;
  }

  penaltyCharge(value) {
    this.properties['abx:penalty_charge'] = { value, type: 'double' };
    return this;
  }

  paymentMethod(value) {
    this.properties['abx:payment_method'] = { value, type: 'string' };
    return this;
  }

  discount(value) {
    this.properties['abx:discount'] = { value, type: 'double' };
    return this;
  }

  signChannel(value) {
    this.properties['abx:sign_channel'] = { value, type: 'string' };
    return this;
  }

  inviteChannel(value) {
    this.properties['abx:invite_channel'] = { value, type: 'string' };
    return this;
  }

  sharingChannel(value) {
    this.properties['abx:sharing_channel'] = { value, type: 'string' };
    return this;
  }

  category1(value) {
    this.properties['abx:category1'] = { value, type: 'string' };
    return this;
  }

  category2(value) {
    this.properties['abx:category2'] = { value, type: 'string' };
    return this;
  }

  category3(value) {
    this.properties['abx:category3'] = { value, type: 'string' };
    return this;
  }

  category4(value) {
    this.properties['abx:category4'] = { value, type: 'string' };
    return this;
  }

  category5(value) {
    this.properties['abx:category5'] = { value, type: 'string' };
    return this;
  }

  keyword(value) {
    this.properties['abx:keyword'] = { value, type: 'string' };
    return this;
  }

  prevVer(value) {
    this.properties['abx:prev_ver'] = { value, type: 'string' };
    return this;
  }

  currVer(value) {
    this.properties['abx:curr_ver'] = { value, type: 'string' };
    return this;
  }

  level(value) {
    this.properties['abx:level'] = { value, type: 'long' };
    return this;
  }

  stage(value) {
    this.properties['abx:stage'] = { value, type: 'string' };
    return this;
  }

  isSkip(value) {
    this.properties['abx:is_skip'] = { value, type: 'boolean' };
    return this;
  }

  customDouble(key, value) {
    this.properties[key] = { value, type: 'double' };
    return this;
  }

  customLong(key, value) {
    this.properties[key] = { value, type: 'long' };
    return this;
  }

  customString(key, value) {
    this.properties[key] = { value, type: 'string' };
    return this;
  }

  customBool(key, value) {
    this.properties[key] = { value, type: 'boolean' };
    return this;
  }


  build() {
    return this.properties;
  }
}

const createBuilderMethod = (methodName) => {
  return function(...args) {
    const builder = new PropertiesBuilder();
    return builder[methodName](...args);
  };
};

const ABProperty = {
  items: createBuilderMethod('items'),
  orderId: createBuilderMethod('orderId'),
  orderSales: createBuilderMethod('orderSales'),
  deliveryCharge: createBuilderMethod('deliveryCharge'),
  penaltyCharge: createBuilderMethod('penaltyCharge'),
  paymentMethod: createBuilderMethod('paymentMethod'),
  discount: createBuilderMethod('discount'),

  signChannel: createBuilderMethod('signChannel'),
  inviteChannel: createBuilderMethod('inviteChannel'),
  sharingChannel: createBuilderMethod('sharingChannel'),

  category1: createBuilderMethod('category1'),
  category2: createBuilderMethod('category2'),
  category3: createBuilderMethod('category3'),
  category4: createBuilderMethod('category4'),
  category5: createBuilderMethod('category5'),
  keyword: createBuilderMethod('keyword'),

  prevVer: createBuilderMethod('prevVer'),
  currVer: createBuilderMethod('currVer'),

  level: createBuilderMethod('level'),
  stage: createBuilderMethod('stage'),
  isSkip: createBuilderMethod('isSkip'),

  customDouble: createBuilderMethod('customDouble'),
  customLong: createBuilderMethod('customLong'),
  customString: createBuilderMethod('customString'),
  customBool: createBuilderMethod('customBool'),
};

const ABItem = () => new ItemBuilder();

window.ABEvent = ABEvent;
window.ABEventProperty = ABEventProperty;
window.ABInviteChannel = ABInviteChannel;
window.ABSharingChannel = ABSharingChannel;
window.ABSignUpChannel = ABSignUpChannel;
window.ABPaymentMethod = ABPaymentMethod;
window.ABCurrency = ABCurrency;

window.ItemBuilder = ItemBuilder;
window.PropertiesBuilder = PropertiesBuilder;
window.ABItem = ABItem;
window.ABProperty = ABProperty;