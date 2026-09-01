import type { Route } from './+types/home';
import { HomeLayout } from 'fumadocs-ui/layouts/home';
import { Link } from 'react-router';
import { baseOptions } from '@/lib/layout.shared';

export function meta({}: Route.MetaArgs) {
  return [
    { title: 'New React Router App' },
    { name: 'description', content: 'Welcome to React Router!' },
  ];
}

export default function Home() {
  return (
    <HomeLayout {...baseOptions()}>
      <div className="p-4 flex flex-col items-center justify-center text-center flex-1">
        <h1 className="text-xl font-bold mb-2">Helix API Documentation</h1>
        <p className="text-fd-muted-foreground mb-4">
          Powered by Vite, React Router and Fumadocs OpenAPI.
        </p>
        <div className="flex items-center gap-3">
          <Link
            className="text-sm bg-fd-primary text-fd-primary-foreground rounded-full font-medium px-4 py-2.5"
            to="/api-reference"
          >
            Open API Reference
          </Link>
          <Link className="text-sm rounded-full font-medium px-4 py-2.5 border" to="/docs">
            Open MDX Docs
          </Link>
        </div>
      </div>
    </HomeLayout>
  );
}
