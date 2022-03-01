/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// identity function for calling harmony imports with the correct context
/******/ 	__webpack_require__.i = function(value) { return value; };
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "/dist/";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 2);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(3)


/***/ }),
/* 1 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(4)


/***/ }),
/* 2 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _furhatCore = __webpack_require__(0);

var _furhatCore2 = _interopRequireDefault(_furhatCore);

var _furhatGui = __webpack_require__(1);

var _furhatGui2 = _interopRequireDefault(_furhatGui);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

(0, _furhatGui2.default)(function (furhat) {
    furhat.subscribe('furhatos.event.responses.ResponseSkillGUIName', function (data) {
        if (data.port == window.location.port) {
            setPageTitle(data.name);
        }
    });

    furhat.subscribe('furhatos.event.actions.ActionSkillGUIClear', function (data) {
        if (data.port == window.location.port) {
            clearScreen();
        }
    });

    furhat.subscribe('furhatos.event.actions.ActionSkillGUIWrite', function (data) {
        if (data.port == window.location.port) {
            clearScreen();
            appendText(data.text);
        }
    });

    furhat.subscribe('furhatos.event.actions.ActionSkillGUIAppend', function (data) {
        if (data.port == window.location.port) {
            appendText(data.text);
        }
    });

    furhat.send({
        event_name: 'furhatos.event.requests.RequestSkillGUIName',
        port: window.location.port
    });
});

function setPageTitle(title) {
    document.getElementsByTagName("title")[0].innerText = title;
}

function appendText(text) {
    var p = document.createElement('p');

    p.innerText = text;
    document.getElementById('root').appendChild(p);
}

function clearScreen() {
    var root = document.getElementById('root');

    while (root.firstChild) {
        root.removeChild(root.firstChild);
    }
}

/***/ }),
/* 3 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});
// Constants
const OPEN = 'open';
const CLOSE = 'closed';
const FAIL = 'failed';

/**
 * Furhat main class. Maintains the websocket connection to furhatOS and
 * has methods to send events, subscribe to events and helper methods such as say,
 * gesture, etc.
 */
class Furhat {
  constructor() {
    this.eventFunctions = {};
  }

  /**
   * Initializes the furhat socket connection and executes the callback method.
   * @param domain IP Address for furhatOS - localhost if SDK.
   * @param port port for RealTimeAPI module of furhatOS.
   * @param route route for RealTimeAPI module of furhatOS.
   * @param callback callback method to be executed on successful opening of websocket.
   */
  init(domain, port, route, callback) {
    if (this.socket !== undefined) {
      this.socket.close();
    }
    console.log(`initializing ws://${domain}:${port}/${route}`); // eslint-disable-line no-console
    this.socket = new window.WebSocket(`ws://${domain}:${port}/${route}`); // eslint-disable-line no-undef

    this.socket.onopen = () => {
      this.status = OPEN;
      if (callback !== undefined) {
        callback(OPEN, this);
      }
    };
    this.socket.onmessage = event => {
      if (this.eventFunctions[JSON.parse(event.data).event_name] !== undefined) {
        this.eventFunctions[JSON.parse(event.data).event_name](JSON.parse(event.data));
      }
    };
    this.socket.onclose = () => {
      this.status = CLOSE;
      if (callback !== undefined) {
        callback(CLOSE, this);
      }
    };
    this.socket.onerror = () => {
      this.status = FAIL;
      if (callback !== undefined) {
        callback(FAIL, this);
      }
    };
  }

  /**
   * Sends an event to furhatOS
   * @param event Object containing the event. Mandtory to have event_name parameter in the object
   */
  send(event) {
    if (this.socket.readyState === 2 || this.socket.readyState === 3) {
      // SHIT
    } else if (this.socket.readyState === 1) {
      this.socket.send(JSON.stringify(event));
    }
  }

  /**
   * Subscribes to the given event and triggers the supplied callback on event
   * @param eventName Name of the event to subscribe
   * @param callback Function which needs to be triggered when the given event is recieved
   * @param dontSend [Optional] [false by default] Boolean which determines wether to send
   * the subscribe event or not. use it to set callbacks for event that are already subscribed to,
   * for instance with group subscriptions
   */
  subscribe(eventName, callback, dontSend = false) {
    const event = { event_name: 'furhatos.event.actions.ActionRealTimeAPISubscribe', name: eventName };
    this.eventFunctions[eventName] = callback;
    if (!dontSend) {
      this.send(event);
    }
  }

  /**
   * Subscribes to the given event group
   * @param groupNumber Number(Assigned ENUM) of the group that needs to be subscribed to
   */
  subscribeGroup(groupNumber) {
    const event = { event_name: 'furhatos.event.actions.ActionRealTimeAPISubscribe', group: groupNumber };
    this.send(event);
  }

  /**
   * Says a given text
   * @param text Text which needs to be said by Furhat
   */
  say(text) {
    const event = { event_name: 'furhatos.event.actions.ActionSpeech', text };
    this.send(event);
  }

  /**
   * Stimulates the speech of a user in the interaction space
   * @param text Text which needs to be said by the user
   */
  userSpeech(text) {
    const event = { event_name: 'furhatos.event.senses.SenseTypingEnd', messageText: text };
    this.send(event);
  }

  /**
   * Stimulates SenseSpeechStart event. Can be used to stimulate user speech via typing
   */
  userSpeechStart() {
    const event = { event_name: 'furhatos.event.senses.SenseTypingStart' };
    this.send(event);
  }

  /**
   * Performs the given gesture
   * @param name Name of the gesture that needs to be performed
   */
  gesture(name) {
    const event = { event_name: 'furhatos.event.actions.ActionGesture', name };
    this.send(event);
  }
}

exports.default = Furhat;

/***/ }),
/* 4 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _furhatCore = __webpack_require__(0);

var _furhatCore2 = _interopRequireDefault(_furhatCore);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

let portNumber;
let callbackFun;

const InitCallback = (status, hat) => {
  if (status === 'open') {
    hat.send({
      event_name: 'furhatos.event.senses.SenseSkillGUIConnected',
      port: portNumber
    });
    callbackFun(hat);
  }
};

/**
 * FurhatGUI Function which sets up a connection to the furhat skill and gives
 * the furhat object to send and recieve events to the skill.
 * @param callback callback that needs to be triggered when a sucessful connection is established
 */
const FurhatGUI = callback => {
  if (callback !== undefined && typeof callback === 'function') {
    window.fetch('/port', { method: 'GET' }).then(r => {
      // eslint-disable-line no-undef
      r.json().then(o => {
        const furhat = new _furhatCore2.default();
        portNumber = o.port;
        callbackFun = callback;
        furhat.init(o.address, o.port, 'api', InitCallback); // eslint-disable-line no-undef
      });
    });
  }
};

exports.default = FurhatGUI;

/***/ })
/******/ ]);