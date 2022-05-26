import os
import click
import uvicorn

PORT = 8083

@click.group()
def cli():
    pass

@cli.group("runserver")
def server():
    pass

@server.command("development")
@click.option(
    "--log-level",
    default="info",
    type=click.Choice(["debug", "info", "warning", "error", "critical"]),
    help="Log level",
)
def run_server(log_level):
    uvicorn.run("src.main:app", log_level="debug", reload=True, host="0.0.0.0", port=PORT)