import path from 'node:path';
import { createOpenAPI } from 'fumadocs-openapi/server';

export const openapi = createOpenAPI({
  input: () => ({
    helix: path.resolve('./openapi/openapi.yaml'),
  }),
});
