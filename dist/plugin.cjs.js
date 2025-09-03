'use strict';

var core = require('@capacitor/core');

core.registerPlugin('CapacitorSmsRetrieverPlugin', {
    web: () => Promise.resolve().then(function () { return web; }).then(m => new m.CapacitorSmsRetrieverWeb()),
});

class CapacitorSmsRetrieverWeb extends core.WebPlugin {
    async getAppHash() {
        throw this.unimplemented('Not implemented on web.');
    }
    async startListening() {
        throw this.unimplemented('Not implemented on web.');
    }
    async stopListening() {
        throw this.unimplemented('Not implemented on web.');
    }
}
new CapacitorSmsRetrieverWeb();

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    CapacitorSmsRetrieverWeb: CapacitorSmsRetrieverWeb
});
//# sourceMappingURL=plugin.cjs.js.map
