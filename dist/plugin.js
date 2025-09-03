(function (core) {
    'use strict';

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

})(capacitorExports);
//# sourceMappingURL=plugin.js.map
