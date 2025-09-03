import { registerPlugin } from '@capacitor/core';
import type { CapacitorSmsRetrieverPlugin } from './definitions';

const CapacitorSmsRetrieverPlugin = registerPlugin<CapacitorSmsRetrieverPlugin>(
  'CapacitorSmsRetrieverPlugin',
  {
    web: () => import('./web').then(m => new m.CapacitorSmsRetrieverWeb()),
  },
);

export * from './definitions';
export { CapacitorSmsRetrieverPlugin };
