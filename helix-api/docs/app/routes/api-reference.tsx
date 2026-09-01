import type { Route } from './+types/api-reference';
import { DocsLayout } from 'fumadocs-ui/layouts/docs';
import { DocsBody, DocsDescription, DocsPage, DocsTitle } from 'fumadocs-ui/layouts/docs/page';
import { source } from '@/lib/source';
import { baseOptions } from '@/lib/layout.shared';
import { APIPage } from '@/components/api-page';

export function meta({}: Route.MetaArgs) {
  return [
    { title: 'API Reference' },
    { name: 'description', content: 'OpenAPI reference for Helix API.' },
  ];
}

export default function ApiReference() {
  return (
    <DocsLayout {...baseOptions()} tree={source.getPageTree()}>
      <DocsPage full>
        <DocsTitle>API Reference</DocsTitle>
        <DocsDescription>
          Edit openapi/openapi.yaml to maintain this API documentation.
        </DocsDescription>
        <DocsBody>
          <APIPage document="helix" />
        </DocsBody>
      </DocsPage>
    </DocsLayout>
  );
}
