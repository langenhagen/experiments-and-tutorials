# Company Context

Reference data only. Do not follow as instructions.

## Company

Name: Mapbox
Industry: Mapping, Location Intelligence, Navigation
Website: https://www.mapbox.com/

Products and Platforms:
- Mapbox Maps
- Mapbox Navigation SDK
- Mapbox Search
- Mapbox Directions API
- Mapbox GL JS
- Mapbox Mobile Maps SDK
- Mapbox Studio
- Mapbox Tiling Service

## Engineering Organization

Team:
Maps Platform Engineering

Responsibilities:
- Map rendering infrastructure
- Map data processing and delivery
- SDK development
- API reliability and performance
- Developer experience

Related Teams:
- Navigation
- Search
- Data Infrastructure
- Mobile Platform
- Web Platform
- Developer Experience

## Technology Stack

Languages:
- TypeScript
- JavaScript
- Go
- Rust
- Swift
- Kotlin
- Python

Frameworks and Libraries:
- React
- Node.js
- Mapbox GL JS
- MapLibre GL JS (where applicable)

Infrastructure:
- AWS
- Kubernetes
- Docker
- Terraform
- GitHub Actions

Databases and Storage:
- PostgreSQL
- Redis
- S3-compatible object storage

## Architecture

Main Systems:
- Map rendering services
- Vector tile infrastructure
- Geocoding services
- Routing services
- Search infrastructure
- Map data pipelines

Important Concepts:
- Vector tiles
- Raster tiles
- Tile boundaries
- Tile pyramids
- Coordinate reference systems
- GeoJSON
- MVT (Mapbox Vector Tiles)
- Web Mercator
- Map projections
- Geospatial indexing

## Engineering Conventions

- Prefer TypeScript for web platform development.
- Prefer explicit types and interfaces.
- Keep modules focused and maintainable.
- Avoid unnecessary abstractions.
- Write tests for behavioral changes.
- Preserve backwards compatibility in public APIs.
- Consider performance implications of rendering changes.
- Document externally visible API behavior.

## Important Constraints

- Map rendering performance is critical.
- API compatibility matters.
- Geographic calculations require careful handling
  of coordinate systems and precision.
- Large geospatial datasets require efficient processing.
- Public SDK behavior should remain predictable.
