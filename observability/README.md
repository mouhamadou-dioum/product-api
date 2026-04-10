# Monitoring et Observabilité - Configuration

## Introduction

Ce document décrit la configuration du système de monitoring et d'observabilité pour l'application Product API.

## Architecture

- **VM1 (192.168.1.34)** : Application Product API (port 8086)
- **VM2 (192.168.1.33)** : Monitoring (Prometheus, Grafana, Loki)

## Composants

### 1. Prometheus

**Rôle** : Collecte des métriques des applications via scraping.

**Configuration** (`prometheus.yml`):
- Intervalle de scraping : 10 secondes
- Endpoint scrapé :
  - Product API : `http://192.168.1.34:8086/actuator/prometheus`

**URL d'accès** : `http://192.168.1.33:9090`

### 2. Grafana

**Rôle** : Visualisation des métriques et des logs.

**Configuration** :
- Datasources configurées :
  - Prometheus : `http://prometheus:9090`
  - Loki : `http://loki:3100`
- Port : 3000
- Identifiants par défaut : admin / admin

**URL d'accès** : `http://192.168.1.33:3000`

### 3. Loki

**Rôle** : Collecte et stockage des logs centralisés.

**Configuration** :
- Port : 3100
- URL d'endpoint : `http://192.168.1.33:3100/loki/api/v1/push`

**URL d'accès** : `http://192.168.1.33:3100`

## Dashboards Grafana

### Product API
1. **Nombre d'appels GET /api/v2/products**
2. **Nombre d'appels POST /api/v2/products/random**

## Configuration de l'application

### Dépendances Maven
- `micrometer-registry-prometheus` - Pour les métriques Prometheus
- `loki-logback-appender` - Pour l'envoi des logs vers Loki

### Endpoints Actuator
- `/actuator/health` - Health check
- `/actuator/info` - Information sur l'application
- `/actuator/prometheus` - Métriques Prometheus
- `/actuator/metrics` - Métriques détaillées

## Déploiement Docker

Pour démarrer les services de monitoring sur VM2 :

```bash
cd observability
docker-compose up -d
```

Pour vérifier les conteneurs actifs :

```bash
docker ps
```
